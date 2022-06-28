package net.zhengzhong.leetcode

import java.io.{File, PrintWriter}
import scala.io.Source
import scala.math.max
import scala.util.{Failure, Success, Try, Using}
import scala.util.matching.Regex

object Main {

  // Relative source directory used to build relative link in `README.md`.
  // See: https://docs.github.com/en/repositories/managing-your-repositorys-settings-and-features/customizing-your-repository/about-readmes#relative-links-and-image-paths-in-readme-files
  val RelSourceDir: String = "src/main/scala/" + getClass.getPackage.getName.replace('.', '/')

  case class Problem(id: Int, title: String, url: String, filename: String) {
    def formatMarkdown(width: Int): (String, String) = {
      val titleMarkdown = s"[$id. $title]($url)".padTo(width, " ").mkString
      val adjustedSourceWidth = width - max(titleMarkdown.length - width, 0)
      val sourceMarkdown = s"[`$filename`]($RelSourceDir/$filename)".padTo(adjustedSourceWidth, " ").mkString
      (titleMarkdown, sourceMarkdown)
    }
  }

  val HeadingPattern: Regex = "^(\\d+)\\.(.+)$".r
  val UrlPattern: Regex = "^(https://leetcode.com/problems/[\\w\\-]+/)$".r

  def loadProblem(file: File): Option[Problem] = {
    val maybeProblem: Try[Problem] = Using(Source.fromFile(file)) { source =>

      val comments = source.getLines()
        .dropWhile(_.trim != "/**").drop(1)
        .takeWhile(_.trim != "*/")
        .map(_.trim.stripPrefix("*").trim)

      val props: Map[String, String] = comments.map { line =>
        val headingMatch = HeadingPattern.findFirstMatchIn(line)
        val urlMatch = UrlPattern.findFirstMatchIn(line)
        (headingMatch, urlMatch) match {
          case (Some(m), None) => Map("id" -> m.group(1), "title" -> m.group(2).trim)
          case (None, Some(m)) => Map("url" -> m.group(1).trim)
          case _ => Map[String, String]()
        }
      }.foldLeft(Map.empty[String, String])(_ ++ _)

      if (props.size != 3) {
        throw new RuntimeException(s"Cannot find LeetCode problem for ${file.getName}")
      }

      Problem(props("id").toInt, props("title"), props("url"), file.getName)
    }

    maybeProblem match {
      case Success(problem) =>
        Some(problem)
      case Failure(e) =>
        println(s"Warning: $e")
        None
    }
  }

  def updateIndex(file: File, content: String): Unit = {
    val outputFile = new File(file.getParentFile, file.getName + ".temp") // Temporary File

    val status: Try[Unit] = Using.Manager { use =>
      val source = use(Source.fromFile(file))
      val writer = use(new PrintWriter(outputFile))

      var insideIndex: Boolean = false
      var indexUpdated: Boolean = false
      for (line <- source.getLines()) {
        if (!insideIndex) {
          writer.println(line)
          if (line.trim == "<!--- INDEX:BEGIN -->") {
            insideIndex = true
            writer.println()
            writer.println(content)
            writer.println()
            indexUpdated = true
          }
        } else {
          if (line.trim == "<!--- INDEX:END -->") {
            insideIndex = false
            writer.println(line)
          }
        }
      }

      if (insideIndex || !indexUpdated) {
        throw new RuntimeException(s"Index section not found in: $file")
      }
    }

    status match {
      case Success(_) =>
        outputFile.renameTo(file)
      case Failure(e) =>
        println(s"Error: $e")
        outputFile.delete()
        throw e
    }
  }

  def main(args: Array[String]): Unit = {
    if (args.length != 1) {
      throw new IllegalArgumentException("Invalid arguments.")
    }

    val projectDir = new File(args(0))
    if (!projectDir.isDirectory) {
      throw new IllegalArgumentException(s"Invalid project directory: $projectDir")
    }

    val sourceDir = new File(projectDir, RelSourceDir)
    if (!sourceDir.isDirectory) {
      throw new IllegalArgumentException(s"Invalid source directory: $sourceDir")
    }
    val sourceFiles = sourceDir.listFiles().filter { file =>
      file.isFile && file.getName.endsWith(".scala") && file.getName != "Main.scala"
    }.toList
    val problems: List[Problem] = sourceFiles.flatMap(loadProblem).sortBy(_.id)

    val cellWidth = 100
    // Each row contains two cells.
    val indexRows: List[(String, String)] = List(
      (
        "Problem".padTo(cellWidth, " ").mkString,
        "Source Code".padTo(cellWidth, " ").mkString,
      ),
      (
        "".padTo(cellWidth, "-").mkString,
        "".padTo(cellWidth, "-").mkString,
      ),
    ) ++ problems.map(_.formatMarkdown(cellWidth))
    val indexContent = indexRows.map(row => s"| ${row._1} | ${row._2} |").mkString("\n")

    val readmeFile = new File(projectDir, "README.md")
    if (!readmeFile.isFile) {
      throw new IllegalArgumentException(s"Invalid project README file: $readmeFile")
    }

    updateIndex(readmeFile, indexContent)
  }
}
