# FETCHER
A race between sync or async runners

## What
The exercice requires to get *current temperatures* for a list of locations provided on the file `capitals.json` that consists on a list of objects like
```json
{
  "CountryName": "Antarctica",
  "CapitalName": "N/A",
  "CapitalLatitude": "0",
  "CapitalLongitude": "0.000000",
  "CountryCode": "AQ",
  "ContinentName": "Antarctica"
}

```
by using the latitude and longitude values any weather API can provide this information and example using Open Meteo

```bash
$ curl https://api.open-meteo.com/v1/forecast?latitude=0&longitude=0&current_weather=true
```

## Output
Print a list for all countries with actual temperature at the coordinates specified like:

```bash
...
Bujumbura: 25.7°
Taipei: 21.9°
Saint Helier: 14.4°
Male: 27.6°
San Salvador: 25.1°
Bamako: 36.5°
Libreville: 30.3°
Castries: 27.9°
Doha: 29.5°
...
```


## Instructions
Create a directory with any name and put your code there, provide a `Dockerfile` file that allow code to run
Copy `Makefile.sample` to you directory for convinience


## Actual results

Actual results in seconds of measuring `time make run`
| Language  | version        | time |
|---|---|---|
| Clojure   | 1.11 / Java 21 | 4.6  |
| GO        | 1.19           | 0.7  |