package database

import model.{Database, Player}

trait IDatabase {

  def create(data:Database):Option[Database]
  def read():Option[Player]
  def update()
  def delete()
}
