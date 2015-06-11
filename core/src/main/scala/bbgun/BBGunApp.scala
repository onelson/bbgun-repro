package bbgun


import scalafx.Includes._
import dispatch._, Defaults._
import org.json4s._
import scalafx.geometry.Insets
import scalafx.scene.layout.{GridPane, VBox, BorderPane}
import scalafx.scene.text.Text


case class Project(id: Int, name: String, description: String, url: String)

class BBGunApp extends BorderPane {

  id = "app"

  val req = Http((
    host("api.github.com", 443).secure / "search" / "repositories" <<? Map(
      "q" -> "robovm+language:scala",
      "sort" -> "stars",
      "order" -> "desc"
    )).GET OK as.json4s.Json)

  implicit val formats = DefaultFormats
  val projects = (req() \ "items").extract[Seq[Project]]

  val projectGrid = new GridPane {
    margin = Insets(8)
    hgap = 4
    vgap = 8
  }

  projects.zipWithIndex.foreach {
    case (proj, idx) =>
      projectGrid.add(new Text(proj.name) {
        prefWidth = 300
      }, 0, idx)
      projectGrid.add(new Text(proj.description) {
        prefWidth = 1000
      }, 1, idx)
      projectGrid.add(new Text(proj.url), 2, idx)
  }

  center = new VBox {
    margin = Insets(20)
    children = Seq(
      new Text("Searching for github projects...."),
      projectGrid
    )
  }


}
