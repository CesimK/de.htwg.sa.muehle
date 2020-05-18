package muehle

import aview.Tui
import com.google.inject.Guice
import controller.MuehleModule
import controllerBaseImpl.Controller
import gui.Gui

import scala.util.Success

object Muehle {
  val controller = Success(new Controller())
  val tui = new Tui(controller)
  val gui = new Gui(controller)

  def main(args: Array[String]): Unit = {
    var input: String = ""
    do {
      input = scala.io.StdIn.readLine()
      tui.process_cmd(input)
    } while (input != "q" && input != "quit")
    System.exit(0)
  }
}
