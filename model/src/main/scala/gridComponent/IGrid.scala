package gridComponent

import scala.collection.mutable.Map
import scala.swing.Publisher

trait IGrid extends Publisher{

  val filled:Array[String]
  def toHTML:String
  def is_free (x: Int): Boolean
}

trait IMill{
  def parse_file(vertex: Map[Int, List[Int]]):Unit
  def connectMills(): List[(Int,Int,Int)]
}
