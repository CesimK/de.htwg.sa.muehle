package database.slick

import controller.controllerBaseImpl.Controller
import database.IDatabaseGame
import model.playerComponent.Player

object RelationalDatabase extends IDatabaseGame {
  private val mappings: CaseClassMapping.type = CaseClassMapping

  def create(controller: Controller): Option[Controller] = {
    try {
      val worked = mappings.create(controller)
      if (worked) {
        println("Saved player in database")
        Some(controller)
      } else {
        println("Error saving player in database")
        None
      }
    } catch {
      case _: Throwable => None  // I WANT TO KILL MYSELF
    }
  }

  def read(p1:String, p2:String): Option[Controller] = {
    try {
      mappings.read(p1, p2)
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
