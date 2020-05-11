package de.htwg.se.muehle.aview.gui

import de.htwg.se.muehle.controller.controllerComponent.IController
import de.htwg.se.muehle.util.{GameOver, GridChanged, InvalidTurn, TakeStone}

import scala.swing.BorderPanel.Position.{Center, North, South}
import scala.swing._
import scala.swing.event._
import scala.util.{Failure, Success, Try}

class SrcSelect extends Event
class DestSelect extends Event

class Gui(controller: Try[IController]) extends MainFrame{
  val outFont = new Font("Ariel", java.awt.Font.PLAIN, 24)
  val statFont = new Font("Ariel", java.awt.Font.PLAIN, 16)
  var moveFrom = -1
  var take = false
  var keineAhnung: IController = _

  controller match {
    case Failure(exception) => Failure(exception)
    case Success(value) => keineAhnung = value
  }

  title = "HTWG Muehle"
  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("New")  {keineAhnung.newGame()})
      contents += new MenuItem(Action("Quit") {System.exit(0)})
      contents += new MenuItem(Action("Save") {keineAhnung.saveGame()})
      contents += new MenuItem(Action("Load") {keineAhnung.loadGame()})
    }
    contents += new Menu("Edit") {
      mnemonic = Key.E
      contents += new MenuItem(Action("Undo") {keineAhnung.undo})
      contents += new MenuItem(Action("Redo") {keineAhnung.redo})
    }
    contents += new Menu("Option") {
      mnemonic = Key.O
      contents += new MenuItem(Action("Show Fieldnumber") {
        canvas.numb = !canvas.numb
        redraw()
      })
    }
  }
  val activeLabel = new Label{
    text = "Active Player:"
    font = outFont
  }
  val activePlayer = new Label {
    text = keineAhnung.active.name
    font = outFont
  }

  val statistics = new GridPanel(1, 2) {
    contents += activeLabel
    contents += activePlayer
  }

  val canvas = new Canvas(keineAhnung) {
    preferredSize = new Dimension(700, 700)
  }
  val status = new Label {
    text = keineAhnung.status
    font = statFont
  }

  contents = new BorderPanel {
    layout(statistics) = North
    layout(canvas) = Center
    layout(status) = South
  }

  listenTo(keineAhnung)
  listenTo(canvas.mouse.clicks)
  reactions += {
    case MouseClicked(_,point,_,_,_) => {
      val pos = check_clicked(point)

      if (pos >= 0 && !take) {
        if (keineAhnung.active.placed < 9) keineAhnung.placeStone(controller, pos)
        else if (moveFrom == -1) {
          if (keineAhnung.checkField(pos)) {
            moveFrom = pos
            keineAhnung.highlight(pos) = true
          }
        }
        else if (moveFrom >= 0 && moveFrom <= keineAhnung.grid.filled.length) {
          keineAhnung.moveStone(moveFrom, pos)
          keineAhnung.highlight(moveFrom) = false
          moveFrom = -1
        }
        else moveFrom = -1
      } else if (take) {
        if (keineAhnung.grid.filled(pos) == keineAhnung.active.color) {
          keineAhnung.status = "Select oponents stone. Not your own ones."
        } else if (keineAhnung.grid.filled(pos) == keineAhnung.grid.empt_val) {
          keineAhnung.status = "Select a field with a stone of your oponent. Empty one is bad"
        } else {
          keineAhnung.removeStone(pos)
          take = false
        }
      }
      redraw()
    }
    case event:GridChanged => redraw()
    case event:InvalidTurn => redraw()
    case event:TakeStone   => takeStone()
    case event:GameOver    => gameOver()
  }
  centerOnScreen()
  visible = true
  redraw()

  def redraw(): Unit = {
    activePlayer.text = keineAhnung.active.name
    status.text = keineAhnung.status
    canvas.redraw()
  }

  def check_clicked(point: Point):Int = {
    val gap = size.width/7
    val row:Int = point.y/gap
    val col:Int = point.x/gap
    var offset = 0
    if (row > 3) offset = 3
    var pos = -1
    row match {
      case 0 | 6 => if (List(0,3,6).contains(col)) pos = row*3 + List(0,3,6).indexOf(col) + offset
      case 1 | 5 => if (List(1,3,5).contains(col)) pos = row*3 + List(1,3,5).indexOf(col) + offset
      case 2 | 4 => if (List(2,3,4).contains(col)) pos = row*3 + List(2,3,4).indexOf(col) + offset
      case 3     => if (List(0,1,2,4,5,6).contains(col)) pos = row*3 + List(0,1,2,4,5,6).indexOf(col)
    }
    pos
  }


  def takeStone(): Unit = {
    keineAhnung.status = "Choose a stone oof yput oponent."
    redraw()
    take = true
  }

  def gameOver(): Unit = {
    val msg:String = "Game ended!\n" + keineAhnung.active.name + " you win the game.\n\nCongratulation."
    val title = "Game Over"
    Dialog.showMessage(contents.head, msg, title)
    System.exit(0)
  }
}
