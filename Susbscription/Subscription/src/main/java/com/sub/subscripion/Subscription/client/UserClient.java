package com.sub.subscripion.Subscription.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserClient {

	private final RestTemplate restTemplate;
	
	public boolean userExists(Long userid) {
		
		String url = "http://localhost:8082/users/" +userid;
		
		try {
			ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
			return response.getStatusCode().is2xxSuccessful();
		}catch (Exception e) {
			return false;
		}
	}
}
