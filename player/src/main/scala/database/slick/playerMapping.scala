package player.database.slick

import player.model.Player

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import slick.driver.PostgresDriver.api._

import scala.collection.immutable.SortedSet

object CaseClassMapping {

  // the base query for the Users table
  val players = TableQuery[playerMapping]

  val db = Database.forURL(
    "jdbc:postgresql://database:5432/player",
    "root", "123",
    null,
    "org.postgresql.Driver")

  def create(player: Player): Boolean = {
    try {
      Await.result(db.run(DBIO.seq(
        // create the schema
        players.schema.create,
        players += dbPlayer(player.name, player.color, player.placed, player.stones, player.mills),
      )), Duration.Inf)
      true
    } catch {
      case err: Exception =>
        println("Error in database", err)
        false;
    }
  }

  def read(name:String): Option[Player] = {
    var player: Option[Player] = None
    Await.result(db.run(DBIO.seq(
      players.result.filter(pl => pl.head.name.equals(name))
        .map(pl => {
        player = Some(Player(pl.head.name, pl.head.color, pl.head.placed, pl.head.stones, pl.head.mills))
      })
    )), Duration.Inf)

    player
  }
}

case class dbPlayer(name:String, color:String, placed:Int, stones:Int, mills:Int)

class playerMapping(tag:Tag) extends Table[dbPlayer](tag, "player") {

  //def id = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name", O.PrimaryKey)
  def color= column[String]("color")
  def placed= column[Int]("placed")
  def stones= column[Int]("stones")
  def mills= column[Int]("mills")

  def * = (name,color, placed, stones, mills) <> (dbPlayer.tupled, dbPlayer.unapply)
}
