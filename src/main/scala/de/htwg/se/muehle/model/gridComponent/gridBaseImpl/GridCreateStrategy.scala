package de.htwg.se.muehle.model.gridComponent.gridBaseImpl

import de.htwg.se.muehle.model.gridComponent.IGrid

trait GridCreateStrategy {

  def createNewGrid(): Grid = {

    var grid = new Grid()
    grid = prepare(grid)
    grid = setGrid(grid)

    grid
  }

  def prepare(grid: Grid):Grid = {
    // by default do nothing
    grid
  }

  def setGrid(grid: IGrid) : Grid // abstract
}
