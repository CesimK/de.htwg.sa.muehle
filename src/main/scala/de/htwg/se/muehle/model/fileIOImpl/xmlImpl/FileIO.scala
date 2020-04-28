package de.htwg.se.muehle.model.fileIOImpl.xmlImpl

import de.htwg.se.muehle.controller.controllerComponent.IController
import de.htwg.se.muehle.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.muehle.model.fileIOImpl.FileIOInterface
import de.htwg.se.muehle.model.gridComponent.gridBaseImpl.Grid
import de.htwg.se.muehle.model.gridComponent.IGrid
import de.htwg.se.muehle.model.playerComponent.Player

import scala.xml.{NodeSeq, PrettyPrinter, Elem}

class FileIO extends FileIOInterface{
  override def load(): IController = {
    val file = scala.xml.XML.loadFile("controller.xml")
    controllerFromXml(file)
  }

  def controllerFromXml(file: Elem): IController = {
    val controller = new Controller(gridFromXml(file \\ "controller" \\ "grid"), playerFromXml((file \\ "controller" \\ "player")(0)), playerFromXml((file \\ "controller" \\ "player")(1)))
    controller.status = (file \ "controller" \ "status").text.toString
    val active_name = (file \ "controller" \ "active").text.toString
    if (active_name == controller.p1.name) controller.active = controller.p1
    else controller.active = controller.p2

    controller
  }

  def gridFromXml(seq: NodeSeq): Grid = {
    new Grid((seq \ "filled").text.toCharArray)
  }

  def playerFromXml(seq: NodeSeq): Player = {
    new Player( (seq \ "name").text,
                (seq \ "color").text.charAt(0),
                (seq \ "placed").text.toInt,
                (seq \"stones").text.toInt,
                (seq \ "mills").text.toInt
              )
  }

  override def save(controller: IController): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("controller.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(controllerToXml(controller))
    pw.write(xml)
    pw.close
  }

  def controllerToXml(controller: IController) = {
    <controller>
      <status>{controller.status}</status>
      <active>{controller.active.name}</active>
      <player>{playerToXml(controller.p1)}</player>
      <player>{playerToXml(controller.p2)}</player>
      <grid>{gridToXml(controller.grid)}</grid>
    </controller>
  }

  def gridToXml(grid: IGrid) = {
    <filled>{grid.filled.toBuffer.toString.replaceAll(", ", "").substring(12,36)}</filled>
  }

  def playerToXml(player: Player) = {
    <name>{player.name}</name>
    <color>{player.color}</color>
    <placed>{player.placed}</placed>
    <stones>{player.stones}</stones>
    <mills>{player.mills}</mills>
  }
}
