package database.slick

import slick.driver.H2Driver.api._

case class DBPlayer(name: String, id:Int)

class playerMapping(tag: Tag) extends Table[DBPlayer](tag, "player") {
  def name = column[String]("grid")
  def color = column[Int]("color")
  def placed = column[Int]("placed")
  def stones = column[Int]("stones")
  def mills = column[Int]("mills")
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def * = (name, id) <> (DBPlayer.tupled, DBPlayer.unapply)

}
