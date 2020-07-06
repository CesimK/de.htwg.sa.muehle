package database.mongo

import database.IDatabaseGrid
import model.gridComponent.gridBaseImpl.Grid
import org.mongodb.scala.{Document, MongoClient, MongoCollection, MongoDatabase}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.{Duration, FiniteDuration}

class gridMongo extends IDatabaseGrid{
  val DURATION: FiniteDuration = Duration.fromNanos(1000000000)
  // To directly connect to the default server localhost on port 27017
  val mongoClient: MongoClient = MongoClient()
  val database: MongoDatabase = mongoClient.getDatabase("muehle")
  val gridCollection: MongoCollection[Document] = database.getCollection("grid")

  override def create(grid: Grid): Future[Grid] = {
    try {
      Await.result(gridCollection.insertOne(
        Document("grid" -> grid.filled.toString, "id" -> grid.id)
      ).toFuture(), DURATION)
      Future.successful(grid)
    } catch {
      case _: Throwable => Future.failed(throw Exception)
    }
  }

//  override def read(id: Int): Option[Grid] = {
//    val x = Await.result(gridCollection.find().toFuture(), DURATION)
//    val list = x
//      .map(doc => (doc.get("grid"), doc.get("id")))
//      .map(tuple => {
//        (tuple._1.getOrElse(return None).asString().getValue, tuple._2.getOrElse(return None).asInt32().getValue)
//      })
//
//    val results = list.map(tuple => {
//      (tuple._1, tuple._2)
//    })
//
//    val correctId = results.find(p => p._2 == id).getOrElse(return None)
//    Some(Grid(correctId._1.toArray[String], correctId._2))
//  }
  override def read(id: Int): Option[Grid] = ???

  override def update(id: Int): Unit = ???

  override def delete(id: Int): Unit = ???

}
