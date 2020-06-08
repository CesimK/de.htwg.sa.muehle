package player

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import player.database.IDatabase
import player.model.Player

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.swing.Reactor

class HttpServer(database: IDatabase) extends Reactor {

  implicit val system: ActorSystem = ActorSystem("my-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher
  var currentPlayer:Player = Player("","")

  val route: Route = {
    get {
      pathSingleSlash {
        complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "<h1>Player Options</h1>"))
      }
      path("player") {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Player Options</h1>" + currentPlayer.playerToHTML))
      }
    } ~
      post {
        pathSingleSlash {
          complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "<h1>Player Options</h1>"))
        }
        path("player" / Segment) { command => {
          process_cmd(command)
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Player Options</h1>" + currentPlayer.playerToHTML))
        }
        }
      }
  }

  val bindingFuture: Future[Http.ServerBinding] = Http().bindAndHandle(route, "0.0.0.0", 8081)

  def unbind(): Unit = {
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }

  def process_cmd(cmd: String): Unit = {
        val tokens = cmd.split(" ")
        tokens(0) match {
          case "p1" | "player1" => database.create(new Player(tokens(1), "W"))
          case "p2" | "player2" => database.create(new Player(tokens(1), "B"))
          case "p" | "player" => database.read(tokens(1)) match {
            case Some(pl) => currentPlayer = pl
          }
          case "r" | "remove" => database.delete(tokens(1))
        }
  }
}
