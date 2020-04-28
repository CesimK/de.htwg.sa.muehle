package de.htwg.se.muehle.controller


import de.htwg.se.muehle.controller.controllerComponent.controllerBaseImpl.Controller
import org.scalatest.{Matchers, WordSpec}
import de.htwg.se.muehle.model.gridComponent.gridBaseImpl._
import de.htwg.se.muehle.model.playerComponent.Player


class ControllerSpec extends WordSpec with Matchers {
  var grid = Grid()
  grid = (new GridCreateGridStrategy).setGrid(grid)
  val player1 = Player("Person 1", 'W')
  val player2 = Player("Person 2", 'B')
  val controller = new Controller(grid, player1, player2)

  "A new Controller" when {
    "has a default constructur with no arguments that always create a new game field" in {
      val new_contr = new Controller()
      new_contr should be equals(controller)
    }
    "can ask its grid to create a String to display" in {
      val def_out = "O-----O-----O\n" +
        "| O---O---O |\n" +
        "| | O-O-O | |\n" +
        "O-O-O   O-O-O\n" +
        "| | O-O-O | |\n" +
        "| O---O---O |\n" +
        "O-----O-----O\n"
      controller.gridToString should be(def_out)
    }
  }

  "Both players can place stones in the beginning" when {
    "The first turn is Player 1 turn " in {
      controller.active should be (controller.p1)
    }
    "The first player can place its stone everywhere" in {
      controller.grid.filled should not contain controller.p1.color
      controller.placeStone(0)
      controller.grid.filled should contain (controller.p1.color)
    }
    "After Player 1 turn it is automatically Player 2 tuen" in {
      controller.active should be (controller.p2)
    }
    "If Player 2 tries to plac his stone in the sam field it will fail." +
    "Also it is still Player 2 turn" in {
      controller.grid.filled should not contain controller.p2.color
      controller.grid.filled should contain (player1.color)
      controller.placeStone(0)
      controller.grid.filled should not contain controller.p2.color
      controller.grid.filled should contain (player1.color)
      controller.active should be (player2)
    }
    "After an allowed turn Player 1 will continue" in {
      controller.grid.filled should not contain controller.p2.color
      controller.placeStone(1)
      controller.grid.filled should contain (controller.p2.color)
      controller.active should be (controller.p1)
    }
    "the Player cannot move his stones until all are placed" in {
      val ref = controller.grid.filled
      controller.moveStone(0, 23)
      controller.grid.filled should be (ref)

    }
    "A Player that has placed all his stones, cannot place more." in {
      controller.p1 = new Player(controller.p1.name, controller.p1.color, 9)
      controller.p1.placed should be (9)
      controller.active = controller.p1
      val tmp = controller.grid.filled
      controller.placeStone( 2)
      controller.grid.filled should be (tmp)
    }
    "But now he can start to move stones" when {
      "the field is not already in use. When in use noting should change." in {
        controller.p1 = new Player(controller.p1.name, controller.p1.color, 9)
        controller.p2 = new Player(controller.p2.name, controller.p2.color, 9)
        controller.p1.placed should be (9)
        controller.p2.placed should be (9)
        controller.active = controller.p1
        controller.grid = new Grid((("W"*9)+("O"*6)+("B"*9)).toCharArray)
        val ref = controller.grid.filled
        controller.moveStone(0, 1)
        controller.grid.filled should be (ref)
        controller.active should be (controller.p1)
        controller.moveStone(9, 10)
        controller.grid.filled should be (ref)
        controller.active should be (controller.p1)
      }
      "the field is still be free, the stone will move" in {
        controller.moveStone(6, 11)
        controller.grid.filled(6) should be (controller.grid.empt_val)
        controller.grid.filled(11) should be (controller.p1.color)
        controller.active should be (controller.p2)
      }
      "Also the other player can move his stones." in {
        controller.active should be (controller.p2)
        controller.moveStone(17, 12)
        controller.grid.filled(17) should be (controller.grid.empt_val)
        controller.grid.filled(12) should be (controller.p2.color)
        controller.active should be (controller.p1)
      }
      "The Game can be saved and load from the controller" in {
        controller.saveGame()
        controller.loadGame() should be equals(controller)
      }
      "The controller can check if a field ebllongs to the active player.\n" +
        "So we can check if the user perform a valid turn." in {
        controller.checkField(1) should be (true)
        controller.checkField(23) should be (false)
        controller.checkField(13) should be (false)
      }

      "A Stone that is taken by a player, must be removed from the field." in {
        controller.removeStone(1)
        controller.grid.filled(1) should be (controller.grid.empt_val)
        controller.active should be (controller.p2)
      }
      "If a player has a mill he can take a stone of the oponent" in {
        controller.grid = new Grid((("W"*9)+("O"*6)+("B"*9)).toCharArray)
        controller.checkForMills() should be ()
      }
      "Players can take the stones of each other and with less than 3 stones one is the winner" in {
        controller.grid = new Grid((("W"*9)+("O"*6)+("B"*9)).toCharArray)
        controller.removeStone(0)
        controller.removeStone(1)
        controller.removeStone(2)
        controller.removeStone(3)
        controller.removeStone(4)
        controller.removeStone(5)

        controller.removeStone(23)
        controller.removeStone(22)
        controller.removeStone(21)
        controller.removeStone(20)
        controller.removeStone(19)
        controller.removeStone(18)

        controller.removeStone(6)
      }
      "start new game" in {
        controller.newGame()
      }
    }
  }
}
