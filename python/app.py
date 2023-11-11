import asyncio
from functools import partial
import httpx
import json


base_url = "https://api.open-meteo.com/v1/forecast?latitude={latitude}&longitude={longitude}&current_weather=true"

def get_temp(response):
    return (response["current_weather"]["temperature"],
            response["current_weather_units"]["temperature"])

def build_url(capital):
    latitude = capital["CapitalLatitude"]
    longitude = capital["CapitalLongitude"]

    return base_url.format(latitude=latitude, longitude=longitude)


async def get_async(client, capital):
    response = await client.get(build_url(capital))

    if response.status_code != 200:
        return f"Error getting {capital['CapitalName']}"

    city = capital["CapitalName"]
    temp, units = get_temp(response.json())
    return f"{city}: {temp} {units}"


async def main():
    client = httpx.AsyncClient()
    with open("capitals.json") as f:
        capitals = json.load(f)

    f_call = partial(get_async, client)
    results = await asyncio.gather(*map(f_call, capitals))
    for r in results:
        print(r)


if __name__ == "__main__":
    asyncio.run(main())
