package de.htwg.se.muehle.controller.controllerComponent.commands

import de.htwg.se.muehle.model.gridComponent.gridBaseImpl.Grid
import de.htwg.se.muehle.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.muehle.util.Command


class PlaceCommand(controller: Controller, pos:Int) extends Command{
  override def doStep: Unit = {
    val edit_grid = controller.grid.filled
    edit_grid(pos) = controller.active.color
    controller.grid = Grid(edit_grid, num_fields = controller.grid.num_fields)
    controller.checkForMills()
    controller.active_Placed.switchActivePlayerPlaced(controller)
  }

  override def undoStep: Unit = {
    val edit_grid = controller.grid.filled
    edit_grid(pos) = controller.grid.empt_val
    controller.grid = Grid(edit_grid, num_fields = controller.grid.num_fields)
    controller.checkForMills()
    controller.active_Placed.switchActivePlayerRemoved(controller)
  }


  override def redoStep: Unit = {
    val edit_grid = controller.grid.filled
    edit_grid(pos) = controller.active.color
    controller.grid = Grid(edit_grid, num_fields = controller.grid.num_fields)
    controller.checkForMills()
    controller.active_Placed.switchActivePlayerPlaced(controller)
  }

}
