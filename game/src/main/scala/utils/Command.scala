package utils

import controller.controllerBaseImpl.Controller

trait Command {
  def doStep: Controller
  def undoStep:Controller
  def redoStep: Controller
}
