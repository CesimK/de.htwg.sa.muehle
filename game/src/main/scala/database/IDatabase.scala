package database

import controller.controllerBaseImpl.Controller

import scala.util.Try

trait IDatabase {
  def create(controller: Try[Controller]): Option[Try[Controller]]

  def read(controller: Try[Controller]): Option[Try[Controller]]

  def update(controller: Try[Controller]): Option[Try[Controller]]

  def delete(controller: Try[Controller]): Option[Try[Controller]]
}
