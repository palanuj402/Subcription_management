package com.sub.subscripion.Subscription.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sub.subscripion.Subscription.entity.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long> {

}
