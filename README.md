# tripInformation
Get information for a trip. Estimate trip duration, find distance to destination, and gather weather information. This REST application consumes Google Maps Distance Matrix API and OpenWeatherMap API. This sample application demonstrates usage of Spring MVC and consumption of public API's through Spring's RestTemplate.

Sample Request: 

{
	"start":"New York City New York",
	"destination":"Washington D.C",
	"mode":"driving"
}

Sample Response:

{
  "duration": "3 hours 56 mins",
  "distance": "226 mi",
  "temperature": 73,
  "high": 75,
  "low": 70,
  "humidity": 37,
  "description": "clear sky"
}
