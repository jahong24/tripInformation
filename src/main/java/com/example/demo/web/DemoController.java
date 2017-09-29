package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.DemoRequest;
import com.example.demo.model.DemoResponse;
import com.example.demo.service.DemoService;

@RestController
@RequestMapping(path = "demo/trip")
public class DemoController {
	
	@Autowired 
	DemoService demoService;
	
	@RequestMapping(method = RequestMethod.POST)
	public DemoResponse addPlayer(@RequestBody DemoRequest demoRequest) {
		return demoService.getInfo(demoRequest); 
	}

}
