package de.htwg.se.muehle.controller.controllerComponent.commands

import de.htwg.se.muehle.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.muehle.model.gridComponent.gridBaseImpl.Grid
import de.htwg.se.muehle.util.Command


class MoveCommand(controller: Controller, src:Int, pos:Int) extends Command {
  override def doStep(): Controller = {
    val edit_grid = controller.grid.filled
    if (controller.active.jump) {
      edit_grid(pos) = controller.active.color
      edit_grid(src) = controller.grid.empt_val
    } else if (controller.isNeighbour(src, pos)) {
      edit_grid(pos) = controller.active.color
      edit_grid(src) = controller.grid.empt_val
    } else {
      controller.state_Moved.selectedFieldNotReachable(controller)
      return controller
    }
    controller.grid = Grid(edit_grid)
    controller.checkForMills()
    controller.active_Moved.switchActivePlayerMoved(controller)
    controller
  }

  override def undoStep: Unit = {
    controller.active_Moved.switchActivePlayerMoved(controller)
    val edit_grid = controller.grid.filled
    edit_grid(src) = controller.active.color
    edit_grid(pos) = controller.grid.empt_val
    controller.grid = Grid(edit_grid)
    controller.checkForMills()
  }

  override def redoStep: Unit = {
    val edit_grid = controller.grid.filled
    edit_grid(pos) = controller.active.color
    edit_grid(src) = controller.grid.empt_val
    controller.grid = Grid(edit_grid)
    controller.checkForMills()
    controller.active_Moved.switchActivePlayerMoved(controller)
  }
}
