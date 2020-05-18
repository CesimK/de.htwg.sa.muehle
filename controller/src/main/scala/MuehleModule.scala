package controller

import com.google.inject.AbstractModule
import controller.IController
import fileIOImpl.{FileIOInterface, jsonImpl}
import net.codingwell.scalaguice.ScalaModule


class MuehleModule extends AbstractModule with ScalaModule {

  def configure() = {
    bind[IController].to[controllerBaseImpl.Controller]
    //bind[IGrid].to[gridBaseImpl.Grid]
    bind[FileIOInterface].to[jsonImpl.FileIO]
  }
}
