package bbgun


import java.util.concurrent.Executors

import grizzled.slf4j.Logging

import scala.concurrent.ExecutionContext
import scalafx.Includes._
import dispatch._, Defaults._
import org.json4s._
import scalafx.application.Platform
import scalafx.geometry.Insets
import scalafx.scene.layout.{GridPane, VBox, BorderPane}
import scalafx.scene.text.Text


case class Project(id: Option[Int], name: Option[String], description: Option[String], url: Option[String])


class BBGunApp extends BorderPane with Logging {
  implicit val executor = new ExecutionContext with Logging {

    def execute(runnable: Runnable) {
      Platform.runLater(runnable)
    }

    def reportFailure(t: Throwable): Unit = error(t)
  }

  implicit val formats = DefaultFormats

  id = "app"

  val projectGrid = new GridPane {
    margin = Insets(8)
    hgap = 4
    vgap = 8
  }

  center = new VBox {
    margin = Insets(20)
    children = Seq(
      new Text("Searching for github projects...."),
      projectGrid
    )
  }

  val base = host("api.github.com", 443).secure / "search" / "repositories" <<? Map(
    "q" -> "robovm+language:scala",
    "sort" -> "stars",
    "order" -> "desc"
  )

  for (resp <- Http(base.GET OK as.json4s.Json)) {
    // This code will execute whenever the Future resolves

    val projects = (resp \ "items").extract[Seq[Project]]
    projects.zipWithIndex.foreach {
      case (proj: Project, idx: Int) =>
        projectGrid.add(new Text(proj.name.getOrElse("")) {
          prefWidth = 300
        }, 0, idx)
        projectGrid.add(new Text(proj.description.getOrElse("")) {
          prefWidth = 1000
        }, 1, idx)
        projectGrid.add(new Text(proj.url.getOrElse("")), 2, idx)
    }
  }


}
