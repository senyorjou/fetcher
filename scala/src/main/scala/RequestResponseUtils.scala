
import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat

case class TemperatureRequest(CountryName:String,
                              CapitalName:String,
                              CapitalLatitude:String,
                              CapitalLongitude:String,
                              CountryCode:String,
                              ContinentName:String)

case class CurrentWeatherUnits(time:String,
                               interval:String,
                               temperature:String,
                               windspeed:String,
                               winddirection:String,
                               is_day:String,
                               weathercode:String)

case class CurrentWeather(time:String,
                          interval:Int,
                          temperature:Double,
                          windspeed:Double,
                          winddirection:Int,
                          is_day:Int,
                          weathercode:Int)

case class TemperatureResponse(
                                latitude:Double,
                                longitude:Double,
                                generationtime_ms:Double,
                                utc_offset_seconds:Int,
                                timezone:String,
                                timezone_abbreviation:String,
                                elevation:Double,
                                current_weather_units:CurrentWeatherUnits,
                                current_weather:CurrentWeather
                              )

object RequestResponseUtils {
  implicit val temperatureRequestFormat: RootJsonFormat[TemperatureRequest] = jsonFormat6(TemperatureRequest.apply)
  implicit val currentWeaterUnitsFormat: RootJsonFormat[CurrentWeatherUnits] = jsonFormat7(CurrentWeatherUnits.apply)
  implicit val currentWeaterFormat: RootJsonFormat[CurrentWeather] = jsonFormat7(CurrentWeather.apply)
  implicit val temperatureResponseFormat: RootJsonFormat[TemperatureResponse] = jsonFormat9(TemperatureResponse.apply)
  implicit val temperatureListRequestFormat: RootJsonFormat[List[TemperatureRequest]] = listFormat
}