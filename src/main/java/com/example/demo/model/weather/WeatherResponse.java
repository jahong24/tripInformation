package com.example.demo.model.weather;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class WeatherResponse {
	
	private List<WeatherListItem> weather;
	private Main main;
	
	public List<WeatherListItem> getWeather() {
		return weather;
	}
	
	public void setWeather(List<WeatherListItem> weather) {
		this.weather = weather;
	}
	
	public Main getMain() {
		return main;
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	
}
