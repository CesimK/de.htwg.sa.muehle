package de.htwg.se.muehle.controller.controllerComponent.commands

import de.htwg.se.muehle.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.muehle.model.gridComponent.gridBaseImpl.Grid
import de.htwg.se.muehle.model.playerComponent.Player
import org.scalatest.{Matchers, WordSpec}

class MoveCommandSpec extends WordSpec with Matchers {
  val grid = Grid(init = true)
  val player1 = Player("Person 1", 'W')
  val player2 = Player("Person 2", 'B')
  val ctrl = new Controller(grid, player1, player2)
  ctrl.p1 = Player(ctrl.p1.name, ctrl.p1.color, 8, 9)
  ctrl.p2 = Player(ctrl.p2.name, ctrl.p2.color, 8, 9)
  ctrl.active = ctrl.p1
  val ref_filled = ctrl.grid.filled
  "A MoveCommand" should {
    "not move a stone when the player can not jump with more than 3 stones" in {
      ctrl.active should be (ctrl.p1)
      ctrl.placeStone(0)
      ctrl.placeStone(1)
      ctrl.active should be (ctrl.p1)
      ctrl.moveStone(0, 23)
      ctrl.grid.filled should be(ref_filled)
      ctrl.active should be (ctrl.p1)
    }
    "place the stone everywhere when only 3 stones left" in {
      ctrl.p1 = Player(ctrl.p1.name, ctrl.p1.color, placed = 9, stones = 3)
      ctrl.p2 = Player(ctrl.p2.name, ctrl.p2.color, placed = 3, stones = 3)
      ctrl.active = ctrl.p1
      ctrl.moveStone(0, 23)
      ctrl.grid.filled(0) should be (ctrl.grid.empt_val)
      ctrl.grid.filled(23) should be (ctrl.p1.color)
      ctrl.active should be (ctrl.p2)
    }
    "undo a turn to old state" in {
      ctrl.undo
      ctrl.grid.filled should be (ref_filled)
      ctrl.active should be (ctrl.p1)
    }

    "redo set the state back before undo" in {
      ctrl.redo
      ctrl.grid.filled(0) should be(ctrl.grid.empt_val)
      ctrl.grid.filled(23) should be(ctrl.p1.color)
      ctrl.active should be(ctrl.p2)
    }
  }
}
