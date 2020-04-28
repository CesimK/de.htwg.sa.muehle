package de.htwg.se.muehle.controller

import de.htwg.se.muehle.controller.controllerComponent.controllerBaseImpl.{Controller, ControllerStateActive}
import de.htwg.se.muehle.model.gridComponent.gridBaseImpl.Grid
import de.htwg.se.muehle.model.playerComponent.Player
import org.scalatest.{Matchers, WordSpec}

class ControllerStateActiveSpec extends WordSpec with Matchers with ControllerStateActive {
  "A CreateStrategy" when { "new" should {
    val grid = Grid(init = true)
    val player1 = Player("Person 1", 'W')
    val player2 = Player("Person 2", 'B')
    val controller = new Controller(grid, player1, player2)
    "should set Active" in {
      setActive(controller)
    }
    "should switch Active Player after Move" in {
//      switchActivePlayerMoved(controller) should be(Unit)
    }
    "should switch Active Player after Place" in {
//      switchActivePlayerPlaced(controller) should be(Unit)
    }
  }}

  override def switchActivePlayerPlaced(controller: Controller): Unit = Unit

  override def switchActivePlayerMoved(controller: Controller): Unit = Unit
}