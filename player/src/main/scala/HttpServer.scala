package player

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.swing.Reactor
import scala.util.{Failure, Success, Try}

class HttpServer(database: Database) extends Reactor {

  implicit val system: ActorSystem = ActorSystem("my-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher
  var currentPlayer:Player = Player("","")

  val route: Route = get {
    pathSingleSlash {
      complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "<h1>Player Options</h1>"))
    }
    path("player") {
      complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Player Options</h1>" + currentPlayer.playerToHTML))
    }
  }
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

  val bindingFuture: Future[Http.ServerBinding] = Http().bindAndHandle(route, "localhost", 8081)

  def unbind(): Unit = {
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }

  def process_cmd(cmd: String): Unit = {
        val tokens = cmd.split(" ")
        tokens(0) match {
          case "p1" | "player1" => database.enterName(tokens(1))
          case "p2" | "player2" => database.enterName(tokens(1))
          case "p" | "player" => currentPlayer = database.enterName(tokens(1))
        }
  }
}
