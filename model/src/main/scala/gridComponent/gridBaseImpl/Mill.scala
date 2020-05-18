package gridComponent.gridBaseImpl


import gridComponent.IMill

import scala.collection.mutable
import scala.io.Source

object Mill extends IMill {
  override def connectMills(): List[(Int, Int, Int)] = {
    var tmp: List[(Int, Int, Int)] = List()
    val file = Source.fromInputStream(getClass().getClassLoader().getResourceAsStream("mills.txt"))
    for (line <- file.getLines()) {
      val tokens = line.split(" ")
      val t1 = tokens(0).toInt
      val t2 = tokens(1).toInt
      val t3 = tokens(2).toInt
      val tupel = (t1, t2, t3)
      tmp = tmp.::(tupel)
    }
    tmp
  }

  override def parse_file(vertex: mutable.Map[Int, List[Int]]): Unit = {
    val stream: Iterator[String] = Source.fromInputStream(getClass.getClassLoader.getResourceAsStream("vertex.txt")).getLines()
    stream.map(line => {
      val tokens = line.split(" ")
      if (vertex.contains(tokens(0).toInt)) {
        vertex(tokens(0).toInt).::(tokens(1).toInt)
      } else {
        vertex += (tokens(0).toInt -> List().::(tokens(1).toInt))
      }
    })
  }

  case class Mill(var mills: List[(Int, Int, Int)] = List()) {
    var vertex: mutable.Map[Int, List[Int]] = mutable.Map[Int, List[Int]]()
    mills = connectMills()
    parse_file(vertex)

    def numMills(posList: Array[Int]): Int = {
      mills.count(e => posList.contains(e._1) && posList.contains(e._2) && posList.contains(e._3))
    }
  }
}

