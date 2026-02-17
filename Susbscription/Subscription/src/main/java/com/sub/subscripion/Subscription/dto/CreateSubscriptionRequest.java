package com.sub.subscripion.Subscription.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateSubscriptionRequest {

	@NotNull
	private Long userId;
	
	@NotNull
	private Long planId;
}
