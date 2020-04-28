package de.htwg.se.muehle.model.fileIOImpl

import de.htwg.se.muehle.controller.controllerComponent.IController

trait FileIOInterface {
  def load():IController
  def save(controller:IController):Unit
}