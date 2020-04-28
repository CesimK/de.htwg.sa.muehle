package de.htwg.se.muehle.util

import de.htwg.se.muehle.controller.controllerComponent.commands.PlaceCommand
import de.htwg.se.muehle.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.muehle.model.gridComponent.gridBaseImpl.Grid
import de.htwg.se.muehle.model.playerComponent.Player
import org.scalatest.{Matchers, WordSpec}

class UndoManagerSpec extends WordSpec with Matchers {

  "An UndoManager" should {
    val undoManager = new UndoManager
    val grid = Grid()
    val player1 = Player("Person 1", 'W')
    val player2 = Player("Person 2", 'B')
    val controller = new Controller(grid, player1, player2)
    controller.newGame()
    val ref_filled = controller.grid.filled
    "have a do, undo and redo" in {
      val command = new PlaceCommand(controller, 0)
      controller.grid.filled should be (ref_filled)
      undoManager.doStep(command)
      controller.grid.filled(0) should be(controller.p1.color)
      undoManager.undoStep
      controller.grid.filled should be (ref_filled)
      undoManager.redoStep
      controller.grid.filled(0) should be(controller.p1.color)
    }
  }
}