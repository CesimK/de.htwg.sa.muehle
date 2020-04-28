package de.htwg.se.muehle.model

import de.htwg.se.muehle.model.playerComponent.Player
import org.scalatest._

class PlayerSpec extends WordSpec with Matchers {
  "A Player" when { "new" should {
    val player = Player("Your Name", 'W')
    "have a name"  in {
      player.name should be("Your Name")
    }
    "have a nice String representation" in {
      player.toString should be("Your Name (W)")
    }
    "Also the player should have the start amount of 9 stone" in {
      player.stones should be(9)
    }
    "With all these stones the player is not be able to jump with his stones" in {
      player.jump should be(false)
    }
    "When the game has not started yet, ther also shoould't be placed a stone." in {
      player.placed should be (0)
    }
  }}
}
