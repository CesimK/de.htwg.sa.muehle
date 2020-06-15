import aview.{HttpServer, Tui}
import aview.gui.Gui
import com.google.inject.Guice
import controller.controllerBaseImpl.Controller
import database.IDatabaseGame
import database.slick.RelationalDatabase

import scala.util.Success

object Muehle {
  val injector = Guice.createInjector(new DatabaseModule)
  val controller = Success(new Controller())
  val tui = new Tui(controller)
  //val gui = new Gui(controller)
  val webserver = new HttpServer(injector.getInstance(classOf[IDatabaseGame]), controller )
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
