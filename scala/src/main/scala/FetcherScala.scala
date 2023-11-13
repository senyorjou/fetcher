import akka.http.scaladsl.model.HttpRequest

import scala.io.Source
import spray.json._
import RequestResponseUtils._
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._

import scala.util.{Failure, Try, _}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


object FetcherScala extends App {
  implicit val system: ActorSystem = ActorSystem()
  val source = Source.fromResource("capitals.json")
  val lines = source.getLines().toList
  source.close()

  Future.traverse(lines.flatMap(raw => raw.parseJson.convertTo[List[TemperatureRequest]])){
    request =>
      sendRequest(request)
        .transform {
          case Success(result) =>
            Success(s"${request.CapitalName}: ${result.current_weather.temperature}${result.current_weather_units.temperature}")
          case Failure(exception) =>
            Success(s"Exception thrown '${exception.getMessage}'")
        }
  }.onComplete {
    case Success(result) =>
      println(result.mkString("\n"))
      system.terminate()
    case Failure(exception) =>
      println(s"Exception thrown '${exception.getMessage}'")
      system.terminate()
  }

  def sendRequest(request: TemperatureRequest):Future[TemperatureResponse] = {
    for {
      request <- Future.fromTry(Try(HttpRequest(uri = s"https://api.open-meteo.com/v1/forecast?latitude=${request.CapitalLatitude}&longitude=${request.CapitalLongitude}&current_weather=true")))
      response <- Http().singleRequest(request)
      parsedResponse <- Unmarshal(response).to[TemperatureResponse]
    } yield parsedResponse
  }
}


