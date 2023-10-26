package main

import (
	"fmt"
)

func getSerial(capitals []Capital) {
	for _, capital := range capitals {
		currentW := getCurrentWeather(capital)
		cityT := parseWeather(currentW)
		fmt.Printf("%s: %2.1f %s\n", capital.CountryName, cityT.CW.Temperature, cityT.CWU.Temperature)
	}
}

func getConcurrent(capitals []Capital) {
	channel := make(chan []byte)
	for _, capital := range capitals {
		go func(capital Capital) {
			channel <- getCurrentWeather(capital)
		}(capital)
	}

	for _, capital := range capitals {
		cityT := parseWeather(<-channel)
		fmt.Printf("%s: %2.1f %s\n", capital.CountryName, cityT.CW.Temperature, cityT.CWU.Temperature)
	}
}

func main() {
	capitals := readJSON()
	getSerial(capitals)
	//getConcurrent(capitals)
}
