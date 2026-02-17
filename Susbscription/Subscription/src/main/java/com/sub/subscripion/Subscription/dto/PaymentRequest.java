package com.sub.subscripion.Subscription.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentRequest {
	
	@NotNull
	private Long userId;
	
	@NotNull
	private Long subscriptionId;
	
	@NotNull
	private Double amount;

}