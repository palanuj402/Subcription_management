package com.sub.subscripion.Subscription.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.sub.subscripion.Subscription.client.BillingClient;
import com.sub.subscripion.Subscription.client.UserClient;
import com.sub.subscripion.Subscription.dto.CreateSubscriptionRequest;
import com.sub.subscripion.Subscription.dto.SubscriptionResponse;
import com.sub.subscripion.Subscription.entity.Plan;
import com.sub.subscripion.Subscription.entity.Subscription;
import com.sub.subscripion.Subscription.entity.SubscriptionStatus;
import com.sub.subscripion.Subscription.exception.PlanNotFoundException;
import com.sub.subscripion.Subscription.exception.SubscriptionAlreadyCancelled;
import com.sub.subscripion.Subscription.exception.SubscriptionNotFoundException;
import com.sub.subscripion.Subscription.exception.UserNotFoundException;
import com.sub.subscripion.Subscription.repository.PlanRepository;
import com.sub.subscripion.Subscription.repository.SubscriptionRepository;
import com.sub.subscripion.Subscription.service.SubscriptionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {

	private final SubscriptionRepository subscriptionRepository;

	private final PlanRepository planRepository;

	private final BillingClient billingClient;

	private final UserClient userClient;

	@Override
	public SubscriptionResponse createSubscription(CreateSubscriptionRequest createSubscriptionRequest) {

		// 1. validate user via User Service
		if (!userClient.userExists(createSubscriptionRequest.getUserId())) {
			throw new UserNotFoundException("User not found with id: ");
		}

		// 2. Fetch Plans
		Plan plan = planRepository.findById(createSubscriptionRequest.getPlanId())
				.orElseThrow(() -> new PlanNotFoundException("Plan Not found"));

		// 3. call payment service
		Subscription subscription = new Subscription();
		subscription.setUserId(createSubscriptionRequest.getUserId());
		subscription.setPlanId(createSubscriptionRequest.getPlanId());
		subscription.setStartDate(LocalDate.now());
		subscription.setEndDate(LocalDate.now().plusDays(plan.getDuratioInDays()));
		subscription.setSubscriptionStatus(SubscriptionStatus.CREATED);

		// save generated id
		subscription = subscriptionRepository.save(subscription);

		// 4. call Payment service
		boolean paymentStatus = billingClient.charge(createSubscriptionRequest.getUserId(), subscription.getId(),
				plan.getPrice());

		// 5. update payment statue
		if (paymentStatus) {
			subscription.setSubscriptionStatus(SubscriptionStatus.ACTIVE);
		} else {
			subscription.setSubscriptionStatus(SubscriptionStatus.PAYMENT_FAILED);
		}

		subscription = subscriptionRepository.save(subscription);

		return mapToResponse(subscription);
	}

	private SubscriptionResponse mapToResponse(Subscription subscription) {

		return SubscriptionResponse.builder().subscriptionId(subscription.getId()).userId(subscription.getUserId())
				.planId(subscription.getPlanId()).status(subscription.getSubscriptionStatus().name())
				.startDate(subscription.getStartDate()).endDate(subscription.getEndDate()).build();
	}

	@Override
	public SubscriptionResponse getSubscription(Long id) {
		Subscription subscription = subscriptionRepository.findById(id)
				.orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found "));

		return mapToResponse(subscription);
	}

	@Override
	public SubscriptionResponse cancelSubscription(Long id) {
		Subscription subscription = subscriptionRepository.findById(id)
				.orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found "));
		if (subscription.getSubscriptionStatus() == SubscriptionStatus.CANCELLED) {
			throw new SubscriptionAlreadyCancelled("Subscription already cancelled");
		} else {
			subscription.setSubscriptionStatus(SubscriptionStatus.CANCELLED);
		}

		subscription = subscriptionRepository.save(subscription);
		return mapToResponse(subscription);
	}

}
