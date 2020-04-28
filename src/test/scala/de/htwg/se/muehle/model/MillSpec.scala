package de.htwg.se.muehle.model

import de.htwg.se.muehle.model.gridComponent.gridBaseImpl.Mill.Mill
import org.scalatest.{Matchers, WordSpec}

class MillSpec extends WordSpec with Matchers {
  "A Mill" when { "new" should {
    val mill = Mill()
    "get mills" in {
     mill.mills should not be(List())
    }
   }
  }
}
