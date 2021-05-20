package com.jarbas.teste.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.jarbas.teste.entities.Address;
import com.jarbas.teste.exception.StandardException;

@Service
public class ApiGoogleService {

	@Value("${app.url}")
	private String url;
	
	@Value("${app.apiKey}")
	private String key;
	
	public String getLatitudeLongitude(Address address) {
		
		String result;
		
		RestTemplate restTemplate = new RestTemplate();
		
		String uri = url + address.getNumber() + "+" 
						 + address.getStreetName() + ",+" 
				         + address.getCity() + ",+"
				         + address.getState() + "&key="
				         + key;
		try {
			
			result = restTemplate.getForObject(uri, String.class);
			
		}catch(HttpClientErrorException e) {
			
			throw new StandardException("Parametros inválidos, favor checar dados enviados!");
		}
		
		return result;
	}
}
