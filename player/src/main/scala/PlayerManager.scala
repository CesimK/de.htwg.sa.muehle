import database.mongo.MongoDB
import player.database.slick._
import player.HttpServer

object PlayerManager {

    val webserver = new HttpServer(new MongoDB())
    def main(args: Array[String]): Unit = {
      var input: String = ""
      do {
        input = scala.io.StdIn.readLine()
        webserver.unbind()
      } while (input != "q" && input != "quit")
      System.exit(0)
    }
}
