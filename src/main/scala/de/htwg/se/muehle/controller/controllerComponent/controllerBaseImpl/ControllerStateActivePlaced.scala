package de.htwg.se.muehle.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.muehle.model.playerComponent.Player

class ControllerStateActivePlaced extends ControllerStateActive {

  def switchActivePlayerPlaced(controller: Controller): Unit = {
    if (controller.active.name.equals(controller.p1.name)) {
      controller.p1 = new Player(controller.p1.name, controller.p1.color, controller.p1.placed + 1, mills = controller.active.mills)
      controller.active = controller.p2
    } else {
      controller.p2 = new Player(controller.p2.name, controller.p2.color, controller.p2.placed + 1, mills = controller.active.mills)
      controller.active = controller.p1
    }
  }

  def switchActivePlayerRemoved(controller: Controller): Unit = {
    if (controller.active.name.equals(controller.p1.name)) {
      controller.p2 = new Player(controller.p2.name, controller.p2.color, controller.p2.placed - 1, mills = controller.active.mills)
      controller.active = controller.p2
    } else {
      controller.p1 = new Player(controller.p1.name, controller.p1.color, controller.p1.placed - 1, mills = controller.active.mills)
      controller.active = controller.p1
    }
  }

  override def switchActivePlayerMoved(controller: Controller): Unit = Unit
}
