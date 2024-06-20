package com.jrtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jrtp.entity.PlanEntity;

public interface PlanRepository extends JpaRepository<PlanEntity, Long> {

}
