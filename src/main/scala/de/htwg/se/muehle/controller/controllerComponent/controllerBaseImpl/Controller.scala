package de.htwg.se.muehle.controller.controllerComponent.controllerBaseImpl

import com.google.inject.{Guice, Inject}
import de.htwg.se.muehle.MuehleModule
import de.htwg.se.muehle.controller.controllerComponent.IController
import de.htwg.se.muehle.controller.controllerComponent.commands.{MoveCommand, PlaceCommand}
import de.htwg.se.muehle.model.fileIOImpl.FileIOInterface
import de.htwg.se.muehle.model.gridComponent.gridBaseImpl.Mill.Mill
import de.htwg.se.muehle.model.gridComponent.gridBaseImpl.{Grid, GridCreateGridStrategy}
import de.htwg.se.muehle.model.playerComponent.Player
import de.htwg.se.muehle.util.{GameOver, GridChanged, InvalidTurn, TakeStone, UndoManager}

import scala.swing.Publisher


class Controller (var grid:Grid, var p1:Player, var p2:Player) extends Publisher with IController {
  var active:Player = p1
  var status:String = " "
  var highlight = Array.fill[Boolean](grid.filled.length)(false)
  val mills:Mill = Mill()
  val state_Placed = new ControllerStateStatusPlaced
  val state_Moved = new ControllerStateStatusMoved
  val active_Placed = new ControllerStateActivePlaced
  val active_Moved = new ControllerStateActiveMoved
  val injector = Guice.createInjector(new MuehleModule)
  val fileio = injector.getInstance(classOf[FileIOInterface])
  private val undo_manager = new UndoManager
  @Inject
  def this () {
    this(Grid(init = true), Player("Cesim Keskin", 'W'), Player("Christopher Gogl", 'B'))
  }
  override def newGame():Unit = {
    grid = (new GridCreateGridStrategy).setGrid(grid)
    p1 = Player(p1.name, 'W')
    p2 = Player(p2.name, 'B')
    active = p1
    publish(new GridChanged)
  }

  override def gridToString: String = grid.toString

  override def placeStone(pos:Int):Unit = {
    if (active.stones != 9 || active.placed >= 9) {
      state_Placed.allStonesPlaced(this)
      publish(new InvalidTurn)
      return
    }
    if (grid.filled(pos) != grid.empty_grid(pos)) {
      state_Placed.slotIsFilled(this)
      publish(new InvalidTurn)
      return
    }
    undo_manager.doStep(new PlaceCommand(this, pos))
    publish(new GridChanged)
  }

  override def moveStone(src:Int, pos:Int):Unit = {
    if (active.placed != 9) {
      state_Moved.stonesStillAvailable(this)
      publish(new InvalidTurn)
      return
    }
    if (!grid.filled(src).equals(active.color)) {
      state_Moved.selectedFieldInvalid(this)
      publish(new InvalidTurn)
      return
    }
    if (!grid.is_free(pos)) {
      state_Moved.selectedFieldNotEmpty(this)
      publish(new InvalidTurn)
      return
    }
    undo_manager.doStep(new MoveCommand(this, src, pos))
    publish(new GridChanged)
  }

  override def undo: Unit = {
    undo_manager.undoStep
    publish(new GridChanged)
  }

  override def redo: Unit = {
    undo_manager.redoStep
    publish(new GridChanged)
  }

  override def isNeighbour(src:Int, dest:Int): Boolean = mills.vertex(src).contains(dest)

  override def checkField(pos:Int):Boolean = {
    if (grid.filled(pos) == active.color) true
    else {
      state_Moved.selectedFieldInvalid(this)
      false
    }
  }

  override def numMills(checkColour: Char): Int = {
    val col_index = this.grid.filled.zipWithIndex.filter(_._1 == checkColour).map(_._2)
    if (col_index.length >= 3) {
      return mills.numMills(col_index)
    }
    0
  }

  override def checkForMills() ={
    val num_mills:Int = this.numMills(this.active.color)
    if (num_mills > this.active.mills) {
      publish(new TakeStone)
    }
    this.active.mills = num_mills
  }

  override def removeStone(pos:Int): Unit = {
    this.grid.filled(pos) = this.grid.empt_val
    if (this.active == this.p1) {
      this.p2 = Player(this.p2.name, this.p2.color, this.p2.placed, this.p2.stones-1, this.p2.mills)
      if (this.p2.stones < 3) {
        publish(new GameOver)
      }
    } else if (this.active == this.p2) {
      this.p1 = Player(this.p1.name, this.p1.color, this.p1.placed, this.p1.stones-1, this.p1.mills)
      if (this.p1.stones < 3) {
        publish(new GameOver)
      }
    }
    this.active_Moved.switchActivePlayerMoved(this)
    publish(new GridChanged)
  }

  override def saveGame(): Unit = {
    fileio.save(this)
  }

  override def loadGame(): Unit = {
    val c = fileio.load()
    this.grid = c.grid
    this.p1 = c.p1
    this.p2 = c.p2
    this.status =c.status
    this.active = c.active
    publish(new GridChanged)
  }
}