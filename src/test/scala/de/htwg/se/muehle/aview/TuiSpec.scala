package de.htwg.se.muehle.aview

import de.htwg.se.muehle.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.muehle.model.gridComponent.gridBaseImpl.Grid
import de.htwg.se.muehle.model.playerComponent.Player
import org.scalatest.{Matchers, WordSpec}

class TuiSpec extends WordSpec with Matchers {
  val grid = Grid()
  val player1 = Player("Person 1", 'W')
  val player2 = Player("Person 2", 'B')
  val controller = new Controller(grid, player1, player2)
  val tui = new Tui(controller)

  "The TUI is the textual method to controll the game." +
  "It can process some commands" when {
    "The TUI process commands it " should {
      "The command execution returns nothing for all commands." in {
        tui.process_cmd("n") should be ()
        tui.process_cmd("new") should be ()
        tui.process_cmd("reset") should be ()
        tui.process_cmd("m") should be ()
        tui.process_cmd("move") should be ()
        tui.process_cmd("u") should be ()
        tui.process_cmd("undo") should be ()
        tui.process_cmd("r") should be ()
        tui.process_cmd("redo") should be ()
        tui.process_cmd("s") should be ()
        tui.process_cmd("save") should be ()
        tui.process_cmd("l") should be ()
        tui.process_cmd("load") should be ()
        tui.process_cmd("sur") should be ()
        tui.process_cmd("surrender") should be ()
        tui.process_cmd("p") should be ()
        tui.process_cmd("place") should be ()
        tui.process_cmd("h") should be ()
        tui.process_cmd("?") should be ()
        tui.process_cmd("help") should be ()
      }
      "An input that isn't in the command set won't trigger an error" in {
        noException should be thrownBy tui.process_cmd("Not in Set")
      }
      "Some commands must be called with arguments" in {
        tui.process_cmd("move 1 2") should be ()
        tui.process_cmd("p 1") should be ()
      }
      "When a status is available the TUI prints it" in {
        controller.status = "A Status Message"
        tui.update
        controller.status should be (" ")
      }
    }
  }
}
