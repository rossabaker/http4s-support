import java.net.InetSocketAddress

import org.http4s._
import org.http4s.dsl._
import org.http4s.server.{Router, ServerApp}
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

  val service = Router(
    "/test1" -> cors(HttpService {
      case GET -> Root => Ok()
    }),
    "/test2" -> cors(HttpService {
      case GET -> Root => Ok()
    })
  )

  override def server(args: List[String]) =
    BlazeBuilder
      .mountService(service)
      .bindSocketAddress(new InetSocketAddress(8000))
      .start
}
