package database.slick

import slick.driver.H2Driver.api._

case class DbGame(grid: String, p1:Int, p2:Int)

class gameMapping(tag: Tag) extends Table[DbGame](tag, "controller") {
  def grid = column[String]("grid")
  def p1 = column[Int]("player1_id")
  def p2 = column[Int]("player2_id")

  def * = (grid,p1,p2) <> (DbGame.tupled, DbGame.unapply)

  def player1 = foreignKey("p1_fk", p1, TableQuery[DBPlayer])(_.id)
  def player2 = foreignKey("p2_fk", p2, TableQuery[DBPlayer])(_.id)
}
