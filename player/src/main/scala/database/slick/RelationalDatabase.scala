package player.database.slick

import player.database.slick.CaseClassMapping
import player.model.Player
import player.database.IDatabase

import scala.concurrent.Future


class RelationalDatabase extends IDatabase {
  private val mappings: CaseClassMapping.type = CaseClassMapping

  def create(player: Player): Future[Player] = {
    try {
      val worked = mappings.create(player)
      if (worked) {
        println("Saved player in database")
        Future.successful(player)
      } else {
        println("Error saving player in database")
        Future.never
      }
    } catch {
      case _: Throwable => Future.never
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
