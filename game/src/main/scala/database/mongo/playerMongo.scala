package database.mongo

import database.IDatabasePlayer
import model.playerComponent.Player
import org.mongodb.scala.{Document, MongoClient, MongoCollection, MongoDatabase}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.{Duration, FiniteDuration}

class playerMongo extends IDatabasePlayer {
  val DURATION: FiniteDuration = Duration.fromNanos(1000000000)
  // To directly connect to the default server localhost on port 27017
  val mongoClient: MongoClient = MongoClient()
  val database: MongoDatabase = mongoClient.getDatabase("muehle")
  val playerCollection: MongoCollection[Document] = database.getCollection("player")

  override def create(player: Player): Future[Player] = {
    try {
      Await.result(playerCollection.insertOne(
        Document("name" -> player.name, "color" -> player.color, "stones" -> player.stones, "mills" -> player.mills)
      ).toFuture(), DURATION)
      Future.successful(player)
    } catch {
      case _: Throwable => Future.never
    }
  }

  override def read(name: String): Option[Player] = {
    val x = Await.result(playerCollection.find().toFuture(), DURATION)
    val list = x
      .map(doc => (doc.get("name"), doc.get("color"), doc.get("stones"), doc.get("mills")))
      .map(tuple => {
        (tuple._1.getOrElse(return None).asString().getValue, tuple._2.getOrElse(return None).asString().getValue,
          tuple._3.getOrElse(return None).asInt32().getValue, tuple._4.getOrElse(return None).asInt32().getValue)
      })

    val results = list.map(tuple => {
      (tuple._1, tuple._2, tuple._3, tuple._4)
    })

    val correctPlayer = results.find(p => p._1 == name).getOrElse(return None)
    Some(Player(correctPlayer._1, correctPlayer._2, correctPlayer._3, correctPlayer._4))
  }

  override def update(name: String): Unit = {
    ???
  }

  override def delete(name: String): Unit = {
    ???
  }
}
