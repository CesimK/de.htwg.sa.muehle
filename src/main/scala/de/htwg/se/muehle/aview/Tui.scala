package de.htwg.se.muehle.aview

import de.htwg.se.muehle.controller.controllerComponent.IController
import de.htwg.se.muehle.util.{GameOver, GridChanged, InvalidTurn, TakeStone}

import util.control.Breaks._
import scala.swing.Reactor

class Tui (val controller: IController) extends Reactor {
  listenTo(controller)
  reactions += {
    case event:GridChanged => update
    case event:InvalidTurn => update
    case event:TakeStone   => takeStone
    case event:GameOver    => gameover
  }

  def process_cmd(cmd:String):Unit = {
    val tokens = cmd.split(" ")
    tokens(0) match {
      case "q" | "quit"          => println("Closing the game. All unsaved changes will be lost.")
      case "n" | "new" | "reset" => controller.newGame()
      case "m" | "move"          => if (tokens.length == 3) controller.moveStone(tokens(1).toInt - 1, tokens(2).toInt - 1)
      case "u" | "undo"          => controller.undo
      case "r" | "redo"          => controller.redo
      case "s" | "save"          => controller.saveGame()
      case "l" | "load"          => controller.loadGame()
      case "p" | "place"         => if (tokens.length == 2) controller.placeStone(tokens(1).toInt - 1)
      case "sur" | "surrender"   => println("Give up")
      case "h" | "?" | "help"    => println(this.help_text())
      case _                     => println("This command does not exists.\nPlease see the help which commands are allowed.")
   }
  }

  def help_text():String =
      "The following commands can be used to controll the game.\n"+
        "To get more information about a command use:\n" +
        "\t<h|help> <command>\n\n" +
        "q | quit:\n" +
        "\tClose the game. The actual progress won't be saved. All changes are lost.\n\n" +
        "f | field:\n" +
        "\tCreate an empty Field.\n\n" +
        "n | new:\n"+
        "\tStart a new game. The current progress get lost and won't be saved.\n\n" +
        "m | move:\n"+
        "\tMove a Stone from one field to another.\n\n" +
        "u | undo:\n"+
        "\tUndo the last turn.\n\n" +
        "r | redo:\n"+
        "\tRedo the last undo turn.\n\n" +
        "s | save:\n"+
        "\tSave the current state of the game.\n\n" +
        "l | load:\n"+
        "\tLoad a previous saved game.\n\n" +
        "p | place\n" +
        "\tTo place stones in the beginning of the game." +
        "sur | surrender:\n" +
        "\tGive up the game. The other player will be declared as winner.\n\n" +
        "h | ? | help:\n" +
        "\tShows this help text."

  def update: Unit = {
    if (!controller.status.equals(" ")) {
      println("Status:\n" + controller.status)
      controller.status = " "
    }
    println("Next Player: " + controller.active)
    println("Stones placed: " + controller.active.placed)
    println("Stones left: " + controller.active.stones)
    println(controller.gridToString)
  }

  def takeStone():Unit = {
    println("Select a Stone of your Oponnent.")
    println("Your colour: " + controller.active.color)
    println(controller.gridToString)
    var input:String = ""
    breakable { while (true) {
      input = scala.io.StdIn.readLine()
      val pos = input.toInt
      if (!(controller.grid.filled(pos) == controller.active.color) && !(controller.grid.filled(pos) == controller.grid.empt_val)) {
        controller.removeStone(pos)
        break
      }
      println("Select a stone of your oponnent.")
    }}
  }

  def gameover(): Unit = {
    println("Game is over.")
    println("Winner is: " + controller.active.name)
    System.exit(0);
  }
}
