package de.htwg.se.muehle.controller.controllerComponent

import de.htwg.se.muehle.model.gridComponent.gridBaseImpl.Grid
import de.htwg.se.muehle.model.playerComponent.Player

import scala.swing.Publisher

trait IController extends Publisher {

  var grid:Grid
  var p1:Player
  var p2:Player
  var active:Player
  var status:String
  var highlight:Array[Boolean]
  def newGame():Unit
  def gridToString: String
  def placeStone(pos:Int):Unit
  def moveStone(src:Int, pos:Int):Unit
  def undo: Unit
  def redo: Unit
  def isNeighbour(src:Int, dest:Int): Boolean
  def checkField(pos:Int):Boolean
  def numMills(checkColour:Char): Int
  def checkForMills(): Unit
  def removeStone(pos:Int): Unit
  def saveGame(): Unit
  def loadGame(): Unit
}