import java.net.InetSocketAddress

import org.http4s._
import org.http4s.dsl._
import org.http4s.server.ServerApp
import org.http4s.server.blaze.BlazeBuilder
import org.http4s.server.middleware.{CORS, CORSConfig}

import scala.concurrent.duration._

object Main extends ServerApp {

  val cors = (s: HttpService) =>
    CORS(
      s,
      CORSConfig(
        anyOrigin = true,
        anyMethod = true,
        maxAge = 1.day.toSeconds,
        allowCredentials = true
      )
    )

  override def server(args: List[String]) =
    BlazeBuilder
      .mountService(cors(HttpService {
        case GET -> Root / "test1" => Ok()
      }))
      .mountService(cors(HttpService {
        case GET -> Root / "test2" => Ok()
      }))
      .bindSocketAddress(new InetSocketAddress(8000))
      .start
}
