package de.htwg.se.muehle.controller


import de.htwg.se.muehle.model.gridComponent.gridBaseImpl._
import org.scalatest.{Matchers, WordSpec}

class GridCreateGridStrategySpec extends WordSpec with Matchers{
  "A Strategy" when { "new" should {
    val grid = new Grid
    val strategy_grid = new GridCreateGridStrategy
    "should create new Grid" in {
//      strategy_grid.setGrid(grid) should be(Grid(filled = grid.empty_grid))
    }
  }}
}
