package com.example.demo.model.google;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class GoogleMapsResponse {
	
	private List<String> destination_addresses;
	private List<String> origin_addresses;
	private List<Elements> rows;
	private String status;
	
	public List<String> getDestination_addresses() {
		return destination_addresses;
	}

	public void setDestination_addresses(List<String> destination_addresses) {
		this.destination_addresses = destination_addresses;
	}

	public List<String> getOrigin_addresses() {
		return origin_addresses;
	}

	public void setOrigin_addresses(List<String> origin_addresses) {
		this.origin_addresses = origin_addresses;
	}

	public List<Elements> getRows() {
		return rows;
	}
	
	public void setRows(List<Elements> rows) {
		this.rows = rows;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
}
