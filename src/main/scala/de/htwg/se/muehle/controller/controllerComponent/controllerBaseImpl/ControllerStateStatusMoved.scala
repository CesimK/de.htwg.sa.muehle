package de.htwg.se.muehle.controller.controllerComponent.controllerBaseImpl

class ControllerStateStatusMoved extends ControllerStateStatus {

  def stonesStillAvailable(controller: Controller): Unit = controller.status = "Place all stones before moving one stone."

  def selectedFieldInvalid(controller: Controller): Unit = controller.status = "At the selected field is none of your stones placed."

  def selectedFieldNotEmpty(controller: Controller): Unit = controller.status = "On this field is already a stone placed.\nChoose another field to place your stone."

  def selectedFieldNotReachable(controller: Controller): Unit = controller.status = "Target Field is no neighbour of your selected field."

  override def allStonesPlaced(controller: Controller): Unit = controller.status = ""

  override def slotIsFilled(controller: Controller): Unit = controller.status = ""
}
