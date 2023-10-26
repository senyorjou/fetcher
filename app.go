package main

import (
	"encoding/json"
	"io/ioutil"
	"log"
	"net/http"
	"net/url"
)

type Capital struct {
	CountryName      string
	CapitalName      string
	CapitalLatitude  string
	CapitalLongitude string
	CountryCode      string
	ContinentName    string
}

type Temperature struct {
	Latitude  float64 `json:"latitude"`
	Longitude float64 `json:"longitude"`
	GenMs     float64 `json:"generationtime_ms"`
	Offset    int     `json:"utc_offset_seconds"`
	TZ        string  `json:"timezone"`
	TZAbbrev  string  `json:"timezone_abbreviation"`
	Elevation float64 `json:"elevation"`
	CWU       struct {
		Time          string `json:"time"`
		Interval      string `json:"interval"`
		Temperature   string `json:"temperature"`
		Windspeed     string `json:"windspeed"`
		WindDirection string `json:"winddirection"`
		IsDay         string `json:"is_day"`
		WeatherCode   string `json:"weathercode"`
	} `json:"current_weather_units"`
	CW struct {
		Time          string  `json:"time"`
		Interval      int     `json:"interval"`
		Temperature   float64 `json:"temperature"`
		WindSpeed     float64 `json:"windspeed"`
		WindDirection int     `json:"winddirection"`
		IsDay         int     `json:"is_day"`
		WeatherCode   int     `json:"weathercode"`
	} `json:"current_weather"`
}

func buildURL(capital Capital) string {
	base, err := url.Parse("https://api.open-meteo.com/v1/forecast")
	if err != nil {
		return "Bad"
	}

	params := url.Values{}
	params.Add("latitude", capital.CapitalLatitude)
	params.Add("longitude", capital.CapitalLongitude)
	params.Add("current_weather", "true")
	base.RawQuery = params.Encode()

	return base.String()
}

func readJSON() []Capital {
	content, err := ioutil.ReadFile("./capitals.json")
	if err != nil {
		log.Fatal("Error when opening file: ", err)
	}

	var capitals []Capital
	err = json.Unmarshal([]byte(content), &capitals)
	if err != nil {
		log.Fatal("Error during Unmarshal(): ", err)
	}

	return capitals
}

func parseWeather(payload []byte) Temperature {

	var currentWeather Temperature
	err := json.Unmarshal(payload, &currentWeather)
	if err != nil {
		log.Fatal("Error during Unmarshal(): ", err)
	}

	return currentWeather
}
func getCurrentWeather(capital Capital) []byte {
	resp, err := http.Get(buildURL(capital))
	if err != nil {
		log.Fatalln(err)
	}
	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		log.Fatalln(err)
	}

	return body
}
