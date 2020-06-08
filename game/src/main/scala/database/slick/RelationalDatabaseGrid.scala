package database.slick

import model.gridComponent.gridBaseImpl.Grid

class RelationalDatabaseGrid {
  private val mappings: CaseClassMappingGrid.type = CaseClassMappingGrid

  def create(grid: Grid): Option[Grid] = {
    try {
      val worked = mappings.create(grid)
      if (worked) {
        println("Saved player in database")
        Some(grid)
      } else {
        println("Error saving player in database")
        None
      }
    } catch {
      case _: Throwable => None
    }
  }

  def read(id: Int): Option[Grid] = {
    try {
      mappings.read(id)
    } catch {
      case _: Throwable => None
    }
  }

  def update(id:Int): Unit = {
    ???
  }

  def delete(id:Int): Unit = {
    ???
  }
}
