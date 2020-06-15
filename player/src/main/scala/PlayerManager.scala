import player.database.slick._
import player.HttpServer
import com.google.inject.Guice
import player.database.IDatabase

object PlayerManager {
    val injector = Guice.createInjector(new PlayerModule)
    val webserver = new HttpServer(injector.getInstance(classOf[IDatabase]))
    def main(args: Array[String]): Unit = {
      var input: String = ""
      do {
        input = scala.io.StdIn.readLine()
        webserver.unbind()
      } while (input != "q" && input != "quit")
      System.exit(0)
    }
}
