package database.mongo

import controller.controllerBaseImpl.Controller
import database.{IDatabaseGame, IDatabaseGrid}
import model.gridComponent.gridBaseImpl.Grid
import org.mongodb.scala.{Document, MongoClient, MongoCollection, MongoDatabase}

import scala.concurrent.Await
import scala.concurrent.duration.{Duration, FiniteDuration}

class gameMongo extends IDatabaseGame {
  val DURATION: FiniteDuration = Duration.fromNanos(1000000000)
  // To directly connect to the default server localhost on port 27017
  val mongoClient: MongoClient = MongoClient()
  val database: MongoDatabase = mongoClient.getDatabase("muehle")
  val gameCollection: MongoCollection[Document] = database.getCollection("game")

  val playerMapping = new playerMongo
  val gridMapping = new gridMongo

  override def create(controller: Controller): Option[Controller] = {
    Await.result(gameCollection.insertOne(
      Document("grid" -> controller.grid.id, "p1" -> controller.p1.name, "p2" -> controller.p2.name)
    ).toFuture(), DURATION)
    Some(controller)
  }

  override def read(p1: String, p2: String): Option[Controller] = {
    val x = gameCollection.find(Document("p1" -> p1, "p2" -> p2)).map(dbo => {
      dbo.getInteger("grid")
    })
    val results = Await.result(x.toFuture(), DURATION)
    val player1 = playerMapping.read(p1)
    val player2 = playerMapping.read(p2)
    val grid = gridMapping.read(results.head)

    Some(new Controller(grid.head, player1.head, player2.head))
  }

  override def update(p1: String, p2: String): Unit = ???

  override def delete(p1: String, p2: String): Unit = ???
}
