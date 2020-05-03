package de.htwg.se.muehle.model.gridComponent.gridBaseImpl

import com.google.inject.Inject
import de.htwg.se.muehle.model.gridComponent.IGrid

case class Grid (filled:Array[String]) extends IGrid {
  val empt_val = "O"

  override def toString: String = {
    val row1 = "x-----x-----x\n"
    val row2 = "| x---x---x |\n"
    val row3 = "| | x-x-x | |\n"
    val mid  = "x-x-x   x-x-x\n"
    val field = row1 + row2 + row3 + mid + row3 + row2 + row1
    field
  }

  override def is_free(pos:Int): Boolean = filled(pos).equals(empt_val)
}
