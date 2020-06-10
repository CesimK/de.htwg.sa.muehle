import aview.{HttpServer, Tui}
import aview.gui.Gui
import controller.controllerBaseImpl.Controller
import database.slick.RelationalDatabase

import scala.util.Success

object Muehle {
  val controller = Success(new Controller())
  val tui = new Tui(controller)
  //val gui = new Gui(controller)
  val webserver = new HttpServer(new RelationalDatabase, controller )
  def main(args: Array[String]): Unit = {
    var input: String = ""
    do {
      input = scala.io.StdIn.readLine()
      tui.process_cmd(input)
      webserver.unbind()
    } while (input != "q" && input != "quit")
    System.exit(0)
  }
}
