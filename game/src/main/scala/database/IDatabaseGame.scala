package database

import controller.controllerBaseImpl.Controller

import scala.concurrent.Future

trait IDatabaseGame {
  def create(controller: Controller): Future[Controller]

  def read(p1:String, p2:String): Future[Controller]

  def update(p1:String, p2:String): Unit

  def delete(p1:String, p2:String): Unit
}
