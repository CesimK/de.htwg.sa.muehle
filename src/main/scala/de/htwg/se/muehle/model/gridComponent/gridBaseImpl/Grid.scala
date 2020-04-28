package de.htwg.se.muehle.model.gridComponent.gridBaseImpl

import com.google.inject.Inject
import de.htwg.se.muehle.model.gridComponent.IGrid

case class Grid (var filled:Array[Char] = Array.empty, init:Boolean = false, num_fields:Int = 24) extends IGrid {
  val empty_field = "O"
  val empt_val = 'O'
  if (init) filled = empty_grid

  @Inject
  def this () {
    this(init = true)
  }
  override def empty_grid:Array[Char] = (empty_field * num_fields).toCharArray

  override def toString: String = {
    val row1 = "x-----x-----x\n"
    val row2 = "| x---x---x |\n"
    val row3 = "| | x-x-x | |\n"
    val mid  = "x-x-x   x-x-x\n"
    var field = row1 + row2 + row3 + mid + row3 + row2 + row1
    for {
      index <- 0 until num_fields
    } field = field.replaceFirst("x", filled(index).toString)
    field
  }

  override def is_free(pos:Int): Boolean = filled(pos).equals(empt_val)
}
