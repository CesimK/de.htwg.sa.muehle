package controller.controllerBaseImpl


trait ControllerStateActive {

  def setActive(controller: Controller):Unit = {
    switchActivePlayerPlaced(controller)
    switchActivePlayerMoved(controller)
  }

  def switchActivePlayerPlaced(controller: Controller): Unit

  def switchActivePlayerMoved(controller: Controller): Unit
}
