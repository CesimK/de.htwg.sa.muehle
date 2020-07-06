package database.slick

import database.IDatabasePlayer
import model.playerComponent.Player

import scala.concurrent.Future

class RelationalDatabasePlayer extends IDatabasePlayer {
  private val mappings: CaseClassMappingPlayer.type = CaseClassMappingPlayer

  def create(player: Player): Future[Player] = {
    try {
      val worked = mappings.create(player)
      if (worked) {
        println("Saved player in database")
        Future.successful(player)
      } else {
        println("Error saving player in database")
        Future.failed(throw Exception)
      }
    } catch {
      case _: Throwable => Future.failed(throw Exception)
    }
  }

  def read(name:String): Option[Player] = {
    try {
      mappings.read(name)
    } catch {
      case _: Throwable => None
    }
  }

  def update(name:String): Unit = {
    ???
  }

  def delete(name:String): Unit = {
    ???
  }
}
