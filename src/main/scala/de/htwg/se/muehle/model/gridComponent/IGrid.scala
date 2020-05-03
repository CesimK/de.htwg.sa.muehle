package de.htwg.se.muehle.model.gridComponent

import scala.collection.mutable.Map
import scala.swing.Publisher

trait IGrid extends Publisher{

  var filled:Array[String]
  val empt_val:String
  def is_free (x: Int): Boolean
}

trait IMill{
  def parse_file(vertex: Map[Int, List[Int]]):Unit
  def connectMills(): List[(Int,Int,Int)]
}