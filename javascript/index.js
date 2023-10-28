const getCountries = require('./src/get-countries');
const getWeather = require('./src/get-weather');

(async () => {
  const countries = getCountries();
  const res = await Promise.all(countries.map(async ({ name, latitude, longitude }) => ({ name, temperature: await getWeather(latitude, longitude)})));
  res.forEach(({ name, temperature }) => {
    console.log(`${name}: ${temperature}`);
  });
})().catch((e) => console.error(e));