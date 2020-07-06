package player.database

import player.model.Player

import scala.concurrent.Future

trait IDatabase {

  def create(player:Player):Future[Player]
  def read(name:String):Future[Player]
  def update(name:String)
  def delete(name:String)
}
