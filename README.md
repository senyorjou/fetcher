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

## Instructions
Create a directory with any name and put your code there, provide a `Dockerfile` file that allow code to run
Copy the Makefile.sample to you directory for convinience

## Actual results

Actual results of measuring `time docker run --rm name-of-the-image`
| Language  | v  | time  |
|---|---|---|
| Clojure  | 1.11 / Java 21  | 5.2  |