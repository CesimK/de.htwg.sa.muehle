package playerManager
import  scala.collection.mutable.Map
class Database {
  var playerLookup:scala.collection.mutable.Map[String, Player] = Map();
  var color = "B"

  def addPlayer(name:String) = {
    if (color.equals("W")) color = "B" else color = "W"
    playerLookup.put(name, new Player(name,color))
  }

  def removePlayer(id:String) = {
    playerLookup.remove(id)
  }
  def enterName(name:String):Player = {
    playerLookup.get(name) match {
      case Some(player) => player
      case None => addPlayer(name).get
    }
  }
}
