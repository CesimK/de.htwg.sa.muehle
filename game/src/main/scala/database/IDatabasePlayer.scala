package database

import model.playerComponent.Player

import scala.concurrent.Future

trait IDatabasePlayer {
  def create(player:Player):Future[Player]
  def read(name:String):Future[Player]
  def update(name:String)
  def delete(name:String)
}
