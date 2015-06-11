package bbgun


import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene

object DesktopApp extends JFXApp {

  val app = new BBGunApp

  stage = new PrimaryStage() {
    title = "BBGun"
    scene = new Scene {
      root = app
      stylesheets = Seq("bbgun/main.css")
    }

  }
}
