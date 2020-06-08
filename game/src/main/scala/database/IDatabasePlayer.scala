package database

import model.playerComponent.Player

trait IDatabasePlayer {
  def create(player:Player):Option[Player]
  def read(name:String):Option[Player]
  def update(name:String)
  def delete(name:String)
}
