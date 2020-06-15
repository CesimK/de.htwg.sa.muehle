package database.mongo

import org.mongodb.scala.Observable._
import org.mongodb.scala._
import org.mongodb.scala.bson.BsonValue
import player.database.IDatabase
import player.model.Player

import scala.concurrent.Await
import scala.concurrent.duration.{Duration, FiniteDuration}

class mongoDB extends IDatabase{
  val DURATION: FiniteDuration = Duration.fromNanos(1000000000)
  // To directly connect to the default server localhost on port 27017
  val mongoClient: MongoClient = MongoClient()
  val database: MongoDatabase = mongoClient.getDatabase("muehle")
  val playerCollection: MongoCollection[Document] = database.getCollection("player")

  override def create(player: Player): Option[Player] = {
    try {
      Await.result(playerCollection.insertOne(
        Document("name" -> player.name, "color" -> player.color, "stones" -> player.stones, "mills" -> player.mills)
      ).toFuture(), DURATION)
      Some(player)
    } catch {
      case _: Throwable => None
    }
  }

  override def read(name: String): Option[Player] = ???

  override def update(name: String): Unit = ???

  override def delete(name: String): Unit = ???
}
