package database

import model.gridComponent.gridBaseImpl.Grid

import scala.concurrent.Future

trait IDatabaseGrid {
  def create(grid: Grid): Future[Grid]

  def read(id: Int): Option[Grid]

  def update(id: Int): Unit

  def delete(id: Int): Unit
}
