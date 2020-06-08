package player.database

import player.model.Player

trait IDatabase {

  def create(player:Player):Option[Player]
  def read(name:String):Option[Player]
  def update(name:String)
  def delete(name:String)
}
