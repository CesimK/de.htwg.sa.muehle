package database

import controller.controllerBaseImpl.Controller

trait IDatabaseGame {
  def create(controller: Controller): Option[Controller]

  def read(p1:String, p2:String): Option[Controller]

  def update(p1:String, p2:String): Unit

  def delete(p1:String, p2:String): Unit
}
