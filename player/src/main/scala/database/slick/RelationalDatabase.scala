package player.database.slick

import player.database.slick.CaseClassMapping
import model.Player
import player.database.IDatabase


object RelationalDatabase extends IDatabase {
  private val mappings: CaseClassMapping.type = CaseClassMapping

  def create(player: Player): Option[Player] = {
    try {
      val worked = mappings.create(player)
      if (worked) {
        println("Saved player in database")
        Some(player)
      } else {
        println("Error saving player in database")
        None
      }
    } catch {
      case _: Throwable => None
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