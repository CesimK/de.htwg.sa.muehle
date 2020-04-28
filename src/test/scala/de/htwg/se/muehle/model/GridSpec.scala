package de.htwg.se.muehle.model.gridComponent.gridBaseImpl

import org.scalatest.{Matchers, WordSpec}

class GridSpec extends WordSpec with Matchers {
  "A Field" when { "new" should {
      val grid = Grid(init = true)
      "create a Grid" in {
        grid.empty_grid.length should be(grid.num_fields)
        for (i <- grid.empty_grid) i.toString should be (grid.empty_field)
      }
      "create a String to display" in {
        val def_out = "O-----O-----O\n" +
          "| O---O---O |\n" +
          "| | O-O-O | |\n" +
          "O-O-O   O-O-O\n" +
          "| | O-O-O | |\n" +
          "| O---O---O |\n" +
          "O-----O-----O\n"
        grid.toString should be (def_out)
      }
    }
  }
  "A Grid that has already stones placed" should {
    "check if it is free or not" in {
      val grid = new Grid((("W"*9)+("B"*9)+("O"*6)).toCharArray)
      grid.is_free(0) should be (false)
      grid.is_free(23) should be (true)
    }
  }
}
