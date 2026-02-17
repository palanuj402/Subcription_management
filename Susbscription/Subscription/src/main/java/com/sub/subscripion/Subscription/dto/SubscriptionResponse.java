package com.sub.subscripion.Subscription.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubscriptionResponse {

	private Long subscriptionId;
	private Long userId;
	private Long planId;
	private String status;
	private LocalDate startDate;
	private LocalDate endDate;
}
