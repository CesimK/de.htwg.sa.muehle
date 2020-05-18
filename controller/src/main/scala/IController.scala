trait IController extends Publisher {

  var grid:Grid
  var p1:Player
  var p2:Player
  var active:Player
  var status:String
  var highlight:Array[Boolean]
  def newGame():Try[IController]
  def gridToString: String
  def placeStone(controller: Try[IController] ,pos:Int):Try[IController]
  def moveStone(controller: Try[IController], src:Int, pos:Int):Try[IController]
  def undo: Unit
  def redo: Unit
  def isNeighbour(src:Int, dest:Int): Boolean
  def checkField(pos:Int):Boolean
  def numMills(checkColour:String): Int
  def checkForMills(): Unit
  def removeStone(controller: Try[IController],pos:Int): Try[IController]
  def saveGame(): Unit
  def loadGame(): Unit
}
