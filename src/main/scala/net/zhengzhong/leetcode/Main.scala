package net.zhengzhong.leetcode

import java.io.File
import scala.io.Source
import scala.math.max
import scala.util.matching.Regex

object Main {

  case class Problem(id: Int, title: String, url: String, filename: String) {
    def formatTitleAndSource(titleWidth: Int, sourceWidth: Int): (String, String) = {
      val titleMarkdown = s"[$id. $title]($url)".padTo(titleWidth, " ").mkString
      val adjustedSourceWidth = sourceWidth - max(titleMarkdown.length - titleWidth, 0)
      val sourceMarkdown = s"`$filename`".padTo(adjustedSourceWidth, " ").mkString
      (titleMarkdown, sourceMarkdown)
    }
  }

  val headingPattern: Regex = "^(\\d+)\\.(.+)$".r
  val urlPattern: Regex = "^(https://leetcode.com/problems/[\\w\\-]+/)$".r

  def loadProblem(file: File): Option[Problem] = {
    val source = Source.fromFile(file)
    val comments = source.getLines().toList
      .dropWhile(_.trim != "/**").drop(1)
      .takeWhile(_.trim != "*/")
      .map(_.trim.stripPrefix("*").trim)

    val props: Map[String, String] = comments.map { line =>
      val headingMatch = headingPattern.findFirstMatchIn(line)
      val urlMatch = urlPattern.findFirstMatchIn(line)
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
    val dir = new File(args(0))
    if (!dir.isDirectory) {
      throw new IllegalArgumentException(s"Invalid directory: $dir")
    }
    val sourceFiles = dir.listFiles().filter { file =>
      file.isFile && file.getName.endsWith(".scala") && file.getName != "Main.scala"
    }.toList
    val problems: List[Problem] = sourceFiles.flatMap(loadProblem).sortBy(_.id)

    val titleWidth = 100
    val sourceWidth = 40
    val rows = List(
      (
        "Problem".padTo(titleWidth, " ").mkString,
        "Source Code".padTo(sourceWidth, " ").mkString,
      ),
      (
        "".padTo(titleWidth, "-").mkString,
        "".padTo(sourceWidth, "-").mkString,
      ),
    ) ++ problems.map(_.formatTitleAndSource(titleWidth, sourceWidth))
    for (row <- rows) {
      println(s"| ${row._1} | ${row._2} |")
    }
  }
}
