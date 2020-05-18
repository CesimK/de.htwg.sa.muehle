package controller.controllerComponent.commands

import model.gridComponent.gridBaseImpl.Grid
import controller.controllerComponent.controllerBaseImpl.Controller
import controller.Command


class PlaceCommand(controller: Controller, pos:Int) extends Command{
  override def doStep: Controller = {
    val edit_grid = controller.grid.filled
    edit_grid(pos) = controller.active.color
    controller.grid = Grid(edit_grid)
    controller.checkForMills()
    controller.active_Placed.switchActivePlayerPlaced(controller)
    controller
  }

  override def undoStep: Controller = {
    val edit_grid = controller.grid.filled
    edit_grid(pos) = controller.grid.empt_val
    controller.grid = Grid(edit_grid)
    controller.checkForMills()
    controller.active_Placed.switchActivePlayerRemoved(controller)
    controller
  }


  override def redoStep: Controller = {
    val edit_grid = controller.grid.filled
    edit_grid(pos) = controller.active.color
    controller.grid = Grid(edit_grid)
    controller.checkForMills()
    controller.active_Placed.switchActivePlayerPlaced(controller)
    controller
  }

}
