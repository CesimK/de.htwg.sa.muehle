package de.htwg.se.muehle.controller

import de.htwg.se.muehle.controller.controllerComponent.controllerBaseImpl.{Controller, ControllerStateActive, ControllerStateStatus}
import de.htwg.se.muehle.model.gridComponent.gridBaseImpl.Grid
import de.htwg.se.muehle.model.playerComponent.Player
import org.scalatest.{Matchers, WordSpec}

class ControllerStateStatusSpec extends WordSpec with Matchers with ControllerStateStatus {
  "A CreateStrategy" when { "new" should {
    val grid = Grid(init = true)
    val player1 = Player("Person 1", 'W')
    val player2 = Player("Person 2", 'B')
    val controller = new Controller(grid, player1, player2)
    "should set state for moved" in {
      setStateMoveStones(controller)
    }
    "should set state for placed" in {
      setStatePlaceStones(controller)
    }
  }}
  override def selectedFieldNotReachable(controller: Controller): Unit = Unit
  override def allStonesPlaced(controller: Controller): Unit = Unit
  override def slotIsFilled(controller: Controller): Unit = Unit
  override def stonesStillAvailable(controller: Controller): Unit = Unit
  override def selectedFieldInvalid(controller: Controller): Unit = Unit
  override def selectedFieldNotEmpty(controller: Controller): Unit = Unit
}