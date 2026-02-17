package com.sub.subscripion.Subscription.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sub.subscripion.Subscription.entity.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

}
