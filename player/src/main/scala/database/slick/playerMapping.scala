package database.slick

import model.Player

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import slick.driver.H2Driver.api._
case class dbPlayer(name:String , id:Int)
class playerMapping(tag:Tag) extends Table[dbPlayer](tag, "player") {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def color= column[String]("color")
  def placed= column[Int]("placed")
  def stones= column[Int]("stones")
  def mills= column[Int]("mills")

  def * = (name, id) <> (dbPlayer.tupled, dbPlayer.unapply)
}
