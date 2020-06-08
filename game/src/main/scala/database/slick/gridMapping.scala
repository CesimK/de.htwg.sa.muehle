package database.slick

import slick.driver.H2Driver.api._

case class DBGrid(filled: String, id:Int)

class playerMapping(tag: Tag) extends Table[DBGrid](tag, "grid") {
  def filled = column[String]("grid")
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def * = (filled, id) <> (DBGrid.tupled, DBGrid.unapply)

}
