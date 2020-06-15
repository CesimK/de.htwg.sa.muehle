package database.mongo

import controller.controllerBaseImpl.Controller
import database.{IDatabaseGame, IDatabaseGrid}
import model.gridComponent.gridBaseImpl.Grid
import org.mongodb.scala.{Document, MongoClient, MongoCollection, MongoDatabase}

import scala.concurrent.duration.{Duration, FiniteDuration}

class gameMongo extends IDatabaseGame {
  val DURATION: FiniteDuration = Duration.fromNanos(1000000000)
  // To directly connect to the default server localhost on port 27017
  val mongoClient: MongoClient = MongoClient()
  val database: MongoDatabase = mongoClient.getDatabase("muehle")
  val gridCollection: MongoCollection[Document] = database.getCollection("grid")
  val playerCollection: MongoCollection[Document] = database.getCollection("player")

  override def create(controller: Controller): Option[Controller] = ???

  override def read(p1: String, p2: String): Option[Controller] = ???

  override def update(p1: String, p2: String): Unit = ???

  override def delete(p1: String, p2: String): Unit = ???
}
