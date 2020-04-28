package de.htwg.se.muehle.model

import de.htwg.se.muehle.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.muehle.model.fileIOImpl._
import org.scalatest.{Matchers, WordSpec}


class FileIoSpec extends WordSpec with Matchers {
  "Progress can be saved either in XML or JSON format." when {
    "The XML writer will save and load the current state in XML." should {
      val controller = new Controller()
      val xmlio = new xmlImpl.FileIO
      "The save function creates or overwrite the xml file with the current settings." in {
        xmlio.save(controller)
      }
      "Load a state, will create the old state to continue playing there." in {
        val load_controller = xmlio.load()
        load_controller.active should be equals (controller.active)
        controller.active should be (controller.p1)
      }
      "Even when the other players turn is, that state will be restored." in {
        controller.active should be (controller.p1)
        controller.placeStone(0)
        controller.active should be (controller.p2)
        xmlio.save(controller)
        val load_controller = xmlio.load()
        load_controller should be equals (controller)
        load_controller.active should be (load_controller.p2)
      }
    }
    "The JSON writer will do the same thing as the XML writer. Only in JSOn format" should {
      val controller = new Controller()
      val jsonio = new jsonImpl.FileIO
      "The save function creates or overwrite the json file with the current settings." in {
        jsonio.save(controller)
      }
      "Load a state, will create the old state to continue playing there." in {
        val load_controller = jsonio.load()
        load_controller.active should be equals (controller.active)
      }
      "Even when the other players turn is, that state will be restored." in {
        controller.placeStone(0)
        jsonio.save(controller)
        val load_controller = jsonio.load()
        load_controller should be equals (controller)
      }
    }
  }
}
