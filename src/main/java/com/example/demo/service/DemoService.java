package com.example.demo.service;

import com.example.demo.model.DemoRequest;
import com.example.demo.model.DemoResponse;

public interface DemoService {
	
	public DemoResponse getInfo(DemoRequest demoRequest);

}
