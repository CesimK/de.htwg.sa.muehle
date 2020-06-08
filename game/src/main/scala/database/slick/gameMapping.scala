package database.slick

import controller.controllerBaseImpl.Controller

import scala.concurrent.duration.Duration
import slick.driver.H2Driver.api._
import slick.lifted.ForeignKeyQuery

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
object CaseClassMapping {

  // the base query for the Users table
  val gameQ = TableQuery[gameMapping]
  val playerMappings: CaseClassMappingPlayer.type = CaseClassMappingPlayer
  val gridMappings: CaseClassMappingGrid.type = CaseClassMappingGrid

  val db = Database.forConfig("h2mem1")

  def create(controller: Controller): Boolean = {
    try {
      Await.result(db.run(DBIO.seq(
        // create the schema
        gameQ.schema.create,
        gameQ += DbGame(controller.grid.id, controller.p1.name, controller.p2.name),
      )), Duration.Inf)
      true
    } catch {
      case err: Exception =>
        println("Error in database", err)
        false;
    }
  }

  def read(p1:String, p2:String): Option[Controller] = {
    var game: Option[Controller] = None
    Await.result(db.run(DBIO.seq(
      gameQ.result.filter(g => g.head.p1 == p1 && g.head.p2 == p2).map(g => {
        val player1 = playerMappings.read(p1)
        val player2 = playerMappings.read(p2)
        val grid = gridMappings.read(g.head.grid)
        game = Some(new Controller(grid.head, player1.head, player2.head))
      }
    ))), Duration.Inf)
      game
  }
}

case class DbGame(grid: Int, p1:String, p2:String)

class gameMapping(tag: Tag) extends Table[DbGame](tag, "controller") {
  def grid = column[Int]("grid")
  def p1 = column[String]("player1_id")
  def p2 = column[String]("player2_id")

  def * = (grid,p1,p2) <> (DbGame.tupled, DbGame.unapply)

  def gridId = foreignKey("gridId_fk", grid, TableQuery[gridMapping])(_.id)
  def player1 = foreignKey("p1_fk", p1, TableQuery[playerMapping])(_.name)
  def player2 = foreignKey("p2_fk", p2, TableQuery[playerMapping])(_.name)
}
