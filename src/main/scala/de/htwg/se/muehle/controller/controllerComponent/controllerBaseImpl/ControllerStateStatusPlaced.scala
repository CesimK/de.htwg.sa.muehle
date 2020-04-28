package de.htwg.se.muehle.controller.controllerComponent.controllerBaseImpl

class ControllerStateStatusPlaced extends ControllerStateStatus {

  def allStonesPlaced(controller: Controller): Unit = controller.status = "All Stones are already placed.\nTo move a stone use the 'move' command."

  def slotIsFilled(controller: Controller): Unit = controller.status = "This field is already blocked.\nSelect another field to place your stone."

  override def stonesStillAvailable(controller: Controller): Unit = controller.status = ""

  override def selectedFieldInvalid(controller: Controller): Unit = controller.status = ""

  override def selectedFieldNotEmpty(controller: Controller): Unit = controller.status = ""

  override def selectedFieldNotReachable(controller: Controller): Unit = controller.status = ""
}
