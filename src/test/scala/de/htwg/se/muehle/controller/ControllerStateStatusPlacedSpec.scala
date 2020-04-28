package de.htwg.se.muehle.controller

import de.htwg.se.muehle.controller.controllerComponent.controllerBaseImpl._
import de.htwg.se.muehle.model.gridComponent.gridBaseImpl.Grid
import de.htwg.se.muehle.model.playerComponent.Player
import org.scalatest.{Matchers, WordSpec}

class ControllerStateStatusPlacedSpec extends WordSpec with Matchers{
  "A State" when { "new" should {
    val status_placed = new ControllerStateStatusPlaced
    val grid = Grid(init = true)
    val player1 = Player("Person 1", 'W')
    val player2 = Player("Person 2", 'B')
    val controller = new Controller(grid, player1, player2)
    "All stones placed" in {
      status_placed.allStonesPlaced(controller) should be (controller.status = "All Stones are already placed.\nTo move a stone use the 'move' command.")
    }
    "slot is filled" in {
      status_placed.slotIsFilled(controller) should be (controller.status = "This field is already blocked.\nSelect another field to place your stone.")
    }
    "stones are still available" in {
      status_placed.stonesStillAvailable(controller) should be (controller.status = "")
    }
    "slot is invalid" in {
      status_placed.selectedFieldInvalid(controller) should be (controller.status = "")
    }
    "selected field is filled" in {
      status_placed.selectedFieldNotEmpty(controller) should be (controller.status = "")
    }
    "selected field not reachable" in {
      status_placed.selectedFieldNotReachable(controller) should be (controller.status = "")
    }
  }}

}
