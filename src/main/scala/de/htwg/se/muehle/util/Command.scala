package de.htwg.se.muehle.util

import de.htwg.se.muehle.controller.controllerComponent.controllerBaseImpl.Controller

trait Command {
  def doStep: Controller
  def undoStep:Controller
  def redoStep: Controller
}
