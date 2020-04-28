package de.htwg.se.muehle.controller.controllerComponent.controllerBaseImpl


class ControllerStateActiveMoved extends ControllerStateActive {

  def switchActivePlayerMoved(controller: Controller): Unit = {

    if (controller.active == controller.p1) {
      controller.active = controller.p2
    } else {
      controller.active = controller.p1
    }
  }

  override def switchActivePlayerPlaced(controller: Controller): Unit = controller.active = null

}
