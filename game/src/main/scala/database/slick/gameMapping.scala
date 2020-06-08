package database.slick

import scala.concurrent.duration.Duration
import controller.controllerBaseImpl.Controller
import slick.driver.H2Driver.api._
import scala.concurrent.Await

case class DbGame(grid: String, p1:Int, p2:Int)

class gameMapping(tag: Tag) extends Table[DbGame](tag, "controller") {
  def grid = column[String]("grid")
  def p1 = column[Int]("player1_id")
  def p2 = column[Int]("player2_id")

  def * = (grid,p1,p2) <> (DbGame.tupled, DbGame.unapply)

  def player1 = foreignKey("p1_fk", p1, TableQuery[dbPlayer])
  def player2 = foreignKey("p2_fk", p2, TableQuery[dbPlayer])
}
