package com.jrtp.entity;

import java.time.LocalDate;

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
	private Long planId;

	private String planName;

	private String planCategory;

	private LocalDate planStartDate;

	private LocalDate planEndDate;

	private String activeSw;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

}
