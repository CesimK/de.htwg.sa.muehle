package de.htwg.se.muehle.controller

import de.htwg.se.muehle.controller.controllerComponent.controllerBaseImpl._
import de.htwg.se.muehle.model.gridComponent.gridBaseImpl.Grid
import de.htwg.se.muehle.model.playerComponent.Player
import org.scalatest.{Matchers, WordSpec}

class ControllerStateActivePlacedSpec extends WordSpec with Matchers{
  "A State" when { "new" should {
    val active_moved = new ControllerStateActivePlaced
    val grid = Grid(init = true)
    val player1 = Player("Person 1", 'W')
    val player2 = Player("Person 2", 'B')
    val controller = new Controller(grid, player1, player2)
    "should switch active player after placed" in {
      controller.active should be(controller.p1)
      active_moved.switchActivePlayerPlaced(controller)
      controller.active should be(controller.p2)
    }
    "should be able to switch player back to first player" in {
      controller.active should be(controller.p2)
      active_moved.switchActivePlayerPlaced(controller)
      controller.active should be(controller.p1)
    }
    "should switch active player after removed" in {
      controller.active should be(controller.p1)
      active_moved.switchActivePlayerRemoved(controller)
      controller.active should be(controller.p2)
    }
    "should be able to switch player back to first player after remove" in {
      controller.active should be(controller.p2)
      active_moved.switchActivePlayerRemoved(controller)
      controller.active should be(controller.p1)
    }
    "should switch active player after moved" in {
      controller.active should be (controller.p1)
      active_moved.switchActivePlayerMoved(controller)
      controller.active should be (controller.p1)
    }
  }}

}