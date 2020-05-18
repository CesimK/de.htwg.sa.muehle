package controller

import controllerBaseImpl.Controller


trait Command {
  def doStep: Controller
  def undoStep:Controller
  def redoStep: Controller
}
