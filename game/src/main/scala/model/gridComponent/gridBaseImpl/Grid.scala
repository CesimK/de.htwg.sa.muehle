package model.gridComponent.gridBaseImpl

import model.gridComponent.IGrid

case class Grid (filled:Array[String], id: Int = -1) extends IGrid {
  val empt_val = "O"

  override def toString: String = {
    val row1 = "x-----x-----x\n"
    val row2 = "| x---x---x |\n"
    val row3 = "| | x-x-x | |\n"
    val mid  = "x-x-x   x-x-x\n"
    var field = row1 + row2 + row3 + mid + row3 + row2 + row1 //TODO: List oder Vector
//    field.map(x => if(x == 'x') 'O' else x)
    for {
      index <- 0 until 24
    } field = field.replaceFirst("x", filled(index).toString)
    field
  }
  override def toHTML: String = "<p  style=\"font-family:'Lucida Console', monospace\"> " + toString.replace("\n","<br>").replace("x","O").replace(" ", "&nbsp") +"</p>"

  override def is_free(pos:Int): Boolean = filled(pos).equals(empt_val)
}
