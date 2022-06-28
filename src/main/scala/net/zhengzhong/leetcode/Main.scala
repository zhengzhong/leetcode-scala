package net.zhengzhong.leetcode

import java.io.File
import scala.io.Source
import scala.math.max
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
    val source = Source.fromFile(file)
    val comments = source.getLines().toList
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

    val problem = if (props.size == 3)
      Some(Problem(props("id").toInt, props("title"), props("url"), file.getName))
    else {
      println(s"Warning: Cannot find LeetCode problem for ${file.getName}")
      None
    }

    source.close()
    problem
  }

  def main(args: Array[String]): Unit = {
    if (args.length != 1) {
      throw new IllegalArgumentException("Invalid arguments.")
    }
    val sourceDir = new File(args(0), RelSourceDir)
    if (!sourceDir.isDirectory) {
      throw new IllegalArgumentException(s"Invalid source directory: $sourceDir")
    }
    val sourceFiles = sourceDir.listFiles().filter { file =>
      file.isFile && file.getName.endsWith(".scala") && file.getName != "Main.scala"
    }.toList
    val problems: List[Problem] = sourceFiles.flatMap(loadProblem).sortBy(_.id)

    val cellWidth = 100
    val rows = List(
      (
        "Problem".padTo(cellWidth, " ").mkString,
        "Source Code".padTo(cellWidth, " ").mkString,
      ),
      (
        "".padTo(cellWidth, "-").mkString,
        "".padTo(cellWidth, "-").mkString,
      ),
    ) ++ problems.map(_.formatMarkdown(cellWidth))
    for (row <- rows) {
      println(s"| ${row._1} | ${row._2} |")
    }
  }
}
