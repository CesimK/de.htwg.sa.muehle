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

class HttpServer(player: Player) extends Reactor {

  implicit val system: ActorSystem = ActorSystem("my-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  val route: Route = get {
    pathSingleSlash {
      complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "<h1>Player Options</h1>"))
    }
    path("player") {
      complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Player Options</h1>" + player.playerToHtml))
    }
  }
  post {
    pathSingleSlash {
      complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "<h1>Player Options</h1>"))
    }
    path("player" / Segment) { command => {
      process_cmd(command)
      complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Player Options</h1>" + player.playerToHtml))
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
          case "p1" | "player1" => player.enterName(player.p1, tokens(1))
          case "p2" | "player2" => player.enterName(player.p2, tokens(1))
          case "p" | "player" => player.getPlayers()
        }
  }
}
