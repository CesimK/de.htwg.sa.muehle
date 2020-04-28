package de.htwg.se.muehle

import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.muehle.controller.controllerComponent.{IController, controllerBaseImpl}
import de.htwg.se.muehle.model.fileIOImpl.{FileIOInterface, jsonImpl, xmlImpl}
import de.htwg.se.muehle.model.gridComponent.{IGrid, gridBaseImpl}


class MuehleModule extends AbstractModule with ScalaModule {

  def configure() = {
    bind[IController].to[controllerBaseImpl.Controller]
    bind[IGrid].to[gridBaseImpl.Grid]
    bind[FileIOInterface].to[jsonImpl.FileIO]
  }
}
