package model.fileIOImpl.jsonImpl

import controller.controllerComponent.IController
import controller.controllerComponent.controllerBaseImpl.Controller
import model.fileIOImpl.FileIOInterface
import model.gridComponent.IGrid
import model.gridComponent.gridBaseImpl.Grid
import model.playerComponent.Player

import play.api.libs.json._
import scala.io._

class FileIO extends FileIOInterface {
  override def load(): IController = {
    val source:String = Source.fromFile("controller.json").getLines.mkString
    val json:JsValue = Json.parse(source)
    controllerFromJson(json)
  }

  def controllerFromJson(json: JsValue):Controller = {
    val controller = new Controller(gridFromJson(json \ "controller" \ "grid"), playerFromJson((json \ "controller" \ "players")(0)), playerFromJson((json \ "controller" \ "players")(1)))
    controller.status = (json \ "controller" \ "status").as[String]
    if ((json \ "controller" \ "active").as[String] == controller.p1.name) controller.active = controller.p1
    else controller.active = controller.p2
    controller

  }

  def gridFromJson(json: JsLookupResult):Grid  = {
    new Grid ((json \ "filled").as[Array[String]])
  }

  def playerFromJson(json: JsValue):Player = {
    new Player( (json \ "name").as[String],
                (json \ "color").as[String],
                (json \ "placed").as[Int],
                (json \ "stones").as[Int],
                (json \ "mills").as[Int])
  }

  override def save(controller: IController): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("controller.json"))
    pw.write(Json.prettyPrint(controllerToJson(controller)))
    pw.close
  }

  def controllerToJson(controller: IController) = {
    Json.obj(
      "controller" -> Json.obj(
        "status" -> controller.status,
        "active" -> controller.active.name,
        "players" -> Json.toJson(
          playerToJson(controller.p1),
          playerToJson(controller.p2)
        ),
        "grid"   -> gridToJson(controller.grid)
      )
    )
  }

  def gridToJson(grid: IGrid) = {
    Json.obj(
      "filled" -> JsString(grid.filled.toBuffer.toString().replaceAll(", ", "").substring(12,36))
    )
  }

  def playerToJson(player: Player) = {
    Json.obj(
      "name" -> player.name,
      "color" -> JsString(player.color.toString),
      "placed" -> JsNumber(player.placed),
      "stones" -> JsNumber(player.stones),
      "mills" -> JsNumber(player.mills)
    )
  }
}
