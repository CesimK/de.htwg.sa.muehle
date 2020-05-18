package model.fileIOImpl

import controller.controllerComponent.IController

trait FileIOInterface {
  def load():IController
  def save(controller:IController):Unit
}