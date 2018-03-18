package com.nextneo.bank.web.service;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class AbstractService {
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

}
