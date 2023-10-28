const fetch = require('node-fetch');

module.exports = async (latitude, longitude) => {
  const res = await fetch(`https://api.open-meteo.com/v1/forecast?latitude=${latitude}&longitude=${longitude}&current_weather=true`);
  const resJson = await res.json();
  return `${resJson?.current_weather?.temperature}${resJson?.current_weather_units?.temperature}` ?? 'N/A';
};