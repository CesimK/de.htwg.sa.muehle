package database.slick

import model.gridComponent.gridBaseImpl.Grid
import slick.driver.H2Driver.api._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration.Duration

object CaseClassMappingGrid {

  // the base query for the Users table
  val gridQ = TableQuery[gridMapping]

  val db = Database.forConfig("h2mem1")

  def create(grid: Grid): Boolean = {
    try {
      Await.result(db.run(DBIO.seq(
        // create the schema
        gridQ.schema.create,
        gridQ += DBGrid(grid.filled.toString, grid.id),
      )), Duration.Inf)
      true
    } catch {
      case err: Exception =>
        println("Error in database", err)
        false;
    }
  }

  def read(id: Int): Option[Grid] = {
    var grid: Option[Grid] = None
    Await.result(db.run(DBIO.seq(
      gridQ.result.filter(g => g.head.id == id).map(g => grid = Some(Grid(g.head.filled.split(""), g.head.id))))
    ), Duration.Inf)
    grid
  }
}
case class DBGrid(filled: String, id:Int)

class gridMapping(tag: Tag) extends Table[DBGrid](tag, "grid") {
  def filled = column[String]("grid")
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def * = (filled, id) <> (DBGrid.tupled, DBGrid.unapply)

}
