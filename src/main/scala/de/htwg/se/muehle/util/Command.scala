package de.htwg.se.muehle.util

trait Command {
  def doStep: Unit
  def undoStep:Unit
  def redoStep: Unit
}
