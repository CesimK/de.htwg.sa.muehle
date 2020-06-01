import player.{HttpServer, Player}

object PlayerManager {

    val webserver = new HttpServer(player)
    def main(args: Array[String]): Unit = {
      var input: String = ""
      do {
        input = scala.io.StdIn.readLine()
        webserver.unbind()
      } while (input != "q" && input != "quit")
      System.exit(0)
    }
}
