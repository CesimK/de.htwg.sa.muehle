package de.htwg.se.muehle.controller.controllerComponent.controllerBaseImpl

trait ControllerStateStatus {

  def setStatePlaceStones(controller: Controller):Unit = {
    allStonesPlaced(controller)
    slotIsFilled(controller)
  }

  def setStateMoveStones(controller: Controller):Unit = {
    stonesStillAvailable(controller)
    selectedFieldInvalid(controller)
    selectedFieldNotEmpty(controller)
    selectedFieldNotReachable(controller)
  }

  def selectedFieldNotReachable(controller: Controller): Unit

  def allStonesPlaced(controller: Controller): Unit

  def slotIsFilled(controller: Controller): Unit

  def stonesStillAvailable(controller: Controller): Unit

  def selectedFieldInvalid(controller: Controller): Unit

  def selectedFieldNotEmpty(controller: Controller): Unit
}
