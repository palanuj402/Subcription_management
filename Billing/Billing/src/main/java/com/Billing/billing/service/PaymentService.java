package com.Billing.billing.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.Billing.billing.dto.PaymentRequest;
import com.Billing.billing.dto.PaymentResponse;
import com.Billing.billing.entity.Payment;
import com.Billing.billing.entity.PaymentStatus;
import com.Billing.billing.repository.PaymentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

	private final PaymentRepository paymentRepository;
	
	@Transactional
	public PaymentResponse processPayment(PaymentRequest paymentRequest) {
		
		Payment payment = Payment.builder()
				.userId(paymentRequest.getUserId())
				.subscripttionId(paymentRequest.getSubscriptionId())
				.status(PaymentStatus.PENDING)
				.createdAt(LocalDateTime.now())
				.build();
		
		payment = paymentRepository.save(payment);
		
		//Simulate payment
		boolean success = Math.random()>0.3;
		
		if(success) {
			payment.setStatus(PaymentStatus.SUCCESS);
		}else {
			payment.setStatus(PaymentStatus.FAILED);
		}
		
		payment =  paymentRepository.save(payment);
		
		return mapToResponse(payment);
	}

	private PaymentResponse mapToResponse(Payment payment) {
		
		return  PaymentResponse.builder()
				.paymentId(payment.getId())
				.status(payment.getStatus().name())
				.build();		
	}
}
