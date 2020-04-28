package de.htwg.se.muehle.model

import de.htwg.se.muehle.model.gridComponent.IGrid
import de.htwg.se.muehle.model.gridComponent.gridBaseImpl.{Grid, GridCreateStrategy}
import org.scalatest.{Matchers, WordSpec}

class GridCreateStrategySpec extends WordSpec with Matchers with GridCreateStrategy {
  "A CreateStrategy" when { "new" should {
    val grid = Grid(init = true)
    "should create a new Grid" in {
      createNewGrid() should be equals (grid)
    }
    "should prepare" in {
      prepare(grid) should be(grid)
    }
    "should create new Grid" in {
//      setGrid(grid:IGrid) should be(grid)
    }
  }}

  override def setGrid(grid: IGrid): Grid = Grid(init = true)
}