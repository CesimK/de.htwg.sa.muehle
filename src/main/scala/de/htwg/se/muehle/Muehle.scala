package de.htwg.se.muehle

import com.google.inject.Guice
import de.htwg.se.muehle.aview.Tui
import de.htwg.se.muehle.aview.gui.Gui
import de.htwg.se.muehle.controller.controllerComponent.IController

object Muehle {
  val injector = Guice.createInjector(new MuehleModule)
  val controller = injector.getInstance(classOf[IController])
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
