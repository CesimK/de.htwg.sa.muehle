package playerManager

class Database {
  var playerLookup:Map[Int, Player] = Map();

  def addPlayer(id:Int, name:String, color:String) = {
    playerLookup = playerLookup + (id, new Player(name,color, id=id))
  }

  def removePlayer(id:Int) = {
    playerLookup = playerLookup - id
  }
}
