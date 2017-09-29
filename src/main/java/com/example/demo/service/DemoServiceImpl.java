package com.example.demo.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.model.DemoRequest;
import com.example.demo.model.DemoResponse;
import com.example.demo.model.google.GoogleMapsResponse;
import com.example.demo.model.weather.WeatherResponse;
import com.example.demo.utility.ConversionUtil;

@Service
public class DemoServiceImpl implements DemoService {

	private static final String GOOGLE_MAPS_URL = "https://maps.googleapis.com/maps/api/distancematrix/json";
	private static final String GOOGLE_MAPS_API_KEY = "AIzaSyDRKJ4UgV4oZXj3CnDJ5pneTuTFdOpveDc";
	private static final String OPEN_WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/weather";
	private static final String OPEN_WEATHER_API_KEY = "c3e98a7f936a0d72b16a7719e41123ce";

	@Autowired
	DemoResponse demoResponse;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	GoogleMapsResponse googleMapsResponse;
	
	@Autowired
	WeatherResponse weatherResponse;

	@Override
	public DemoResponse getInfo(DemoRequest demoRequest) {

		/* Google Maps Distance Matrix API */
		googleMapsResponse = getMaps(demoRequest);
		demoResponse.setDistance(googleMapsResponse.getRows().get(0).getElements().get(0).getDistance().getText());
		demoResponse.setDuration(googleMapsResponse.getRows().get(0).getElements().get(0).getDuration().getText());

		/* OpenWeatherMap Current API */
		weatherResponse = getWeather(demoRequest);
		demoResponse.setTemperature(ConversionUtil.convertKelvinToFahrenheit(weatherResponse.getMain().getTemp()));
		demoResponse.setHigh(ConversionUtil.convertKelvinToFahrenheit(weatherResponse.getMain().getTemp_max()));
		demoResponse.setLow(ConversionUtil.convertKelvinToFahrenheit(weatherResponse.getMain().getTemp_min()));
		demoResponse.setHumidity(weatherResponse.getMain().getHumidity());
		demoResponse.setDescription(weatherResponse.getWeather().get(0).getDescription());

		return demoResponse;
	}

	public GoogleMapsResponse getMaps(DemoRequest demoRequest) {

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(GOOGLE_MAPS_URL);
		builder.queryParam("units", "imperial");
		builder.queryParam("origins", demoRequest.getStart().replaceAll(" ", "+"));
		builder.queryParam("destinations", demoRequest.getDestination().replaceAll(" ", "+"));
		builder.queryParam("mode", demoRequest.getMode());
		builder.queryParam("key", GOOGLE_MAPS_API_KEY);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		ResponseEntity<GoogleMapsResponse> response = restTemplate.exchange(
				builder.toUriString().replaceAll("%2B", "+"), HttpMethod.GET, requestEntity, GoogleMapsResponse.class);
		GoogleMapsResponse googleMapsResponse = response.getBody();

		return googleMapsResponse;
	}

	public WeatherResponse getWeather(DemoRequest demoRequest) {

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(OPEN_WEATHER_API_URL);
		builder.queryParam("q", demoRequest.getDestination().replaceAll(" ", "+"));
		builder.queryParam("appid", OPEN_WEATHER_API_KEY);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		ResponseEntity<WeatherResponse> response = restTemplate.exchange(builder.toUriString().replaceAll("%2B", "+"),
				HttpMethod.GET, requestEntity, WeatherResponse.class);
		WeatherResponse weatherResponse = response.getBody();

		return weatherResponse;
	}

}
