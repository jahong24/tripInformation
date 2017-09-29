package com.example.demo.utility;

public class ConversionUtil {
	
	public static double convertKelvinToFahrenheit(double kelvin) {
		
		double degrees = (kelvin * 1.8) - 459.67;
		
		return Math.round(degrees);
		
	}

}
