package com.sub.subscripion.Subscription.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sub.subscripion.Subscription.dto.PaymentRequest;
import com.sub.subscripion.Subscription.dto.PaymentResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BillingClient {

	private final RestTemplate restTemplate;
	
	public boolean charge(Long userId, Long subScriptionId, Double amount) {
		String url = "http://localhost:8084/payment";
		
		PaymentRequest request = new PaymentRequest();
		request.setUserId(userId);
		request.setSubscriptionId(subScriptionId);
		request.setAmount(amount);
		
		ResponseEntity<PaymentResponse> response = restTemplate.postForEntity(url, request, PaymentResponse.class);
		
		return "SUCCESS".equals(response.getBody().getStatus());
	}
}
