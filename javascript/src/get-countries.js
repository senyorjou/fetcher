const countries = require("../capitals.json");

module.exports = () => countries.map(({ CountryName, CapitalLatitude, CapitalLongitude }) => ({ name: CountryName, latitude: CapitalLatitude, longitude: CapitalLongitude}));