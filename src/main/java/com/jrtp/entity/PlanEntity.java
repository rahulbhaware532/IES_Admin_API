package com.jrtp.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "IES_PLANS")
public class PlanEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PLAN_ID")
	private Long planId;

	@Column(name = "PLAN_NAME", nullable = false)
	private String planName;

	@Column(name = "PLAN_CATEGORY", nullable = false)
	private String planCategory;

	@Column(name = "PLAN_START_DATE", nullable = false)
	private LocalDate planStartDate;

	@Column(name = "PLAN_END_DATE", nullable = false)
	private LocalDate planEndDate;

	@Column(name = "ACTIVE_SW", nullable = false)
	private String activeSw;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private UserEntity user;

}
