package fileIOImpl

import controller.IController


trait FileIOInterface {
  def load():IController
  def save(controller:IController):Unit
}
