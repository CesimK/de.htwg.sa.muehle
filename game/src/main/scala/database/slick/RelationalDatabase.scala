package database.slick

import controller.controllerBaseImpl.Controller
import database.IDatabaseGame
import model.playerComponent.Player

import scala.concurrent.Future

class RelationalDatabase extends IDatabaseGame {
  private val mappings: CaseClassMapping.type = CaseClassMapping

  def create(controller: Controller): Future[Controller] = {
    try {
      val worked = mappings.create(controller)
      if (worked) {
        println("Saved player in database")
        Future.successful(controller)
      } else {
        println("Error saving player in database")
        Future.failed(throw Exception)
      }
    } catch {
      case _: Throwable => Future.failed(throw Exception)
    }
  }

  def read(p1:String, p2:String): Option[Controller] = {
    try {
      mappings.read(p1, p2)
    } catch {
      case _: Throwable => None
    }
  }

  def update(p1:String, p2:String): Unit = {
    ???
  }

  def delete(p1:String, p2:String): Unit = {
    ???
  }
}
