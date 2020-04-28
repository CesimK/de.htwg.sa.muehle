package de.htwg.se.muehle.controller

import de.htwg.se.muehle.controller.controllerComponent.controllerBaseImpl._
import de.htwg.se.muehle.model.gridComponent.gridBaseImpl.Grid
import de.htwg.se.muehle.model.playerComponent.Player
import org.scalatest.{Matchers, WordSpec}

class ControllerStateStatusMovedSpec extends WordSpec with Matchers{
  "A State" when { "new" should {
    val state_moved = new ControllerStateStatusMoved
    val grid = Grid(init = true)
    val player1 = Player("Person 1", 'W')
    val player2 = Player("Person 2", 'B')
    val controller = new Controller(grid, player1, player2)
    "all Stones are Placed" in {
      state_moved.allStonesPlaced(controller) should be (controller.status = "")
    }
    "slot is filled" in {
      state_moved.slotIsFilled(controller) should be (controller.status = "")
    }
    "selected field not reachable" in {
      state_moved.selectedFieldNotReachable(controller) should be (controller.status = "Target Field is no neighbour of your selected field.")
    }
    "stones still available" in {
      state_moved.stonesStillAvailable(controller) should be (controller.status = "Place all stones before moving one stone.")
    }
    "selected field is invalid" in {
      state_moved.selectedFieldInvalid(controller) should be (controller.status = "At the selected field is none of your stones placed.")
    }
    "selected field is not empty" in {
      state_moved.selectedFieldNotEmpty(controller) should be (controller.status = "On this field is already a stone placed.\nChoose another field to place your stone.")
    }
  }}

}
