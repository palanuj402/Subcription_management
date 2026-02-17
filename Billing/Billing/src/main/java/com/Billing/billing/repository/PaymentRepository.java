package com.Billing.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Billing.billing.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
