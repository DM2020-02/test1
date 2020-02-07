package com.jt.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

	@Value("${server.port}")
	private String port;
	
	@RequestMapping("/getport")
	public String getPort() {
		return "server.port: "+port;
	}
	
}
