package database

import model.gridComponent.gridBaseImpl.Grid

trait IDatabaseGrid {
  def create(grid: Grid): Option[Grid]

  def read(id: Int): Option[Grid]

  def update(id: Int): Unit

  def delete(id: Int): Unit
}
