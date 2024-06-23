package com.jrtp.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "IES_USERS")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	private String fullName;

	private String email;

	private String pwd;

	private String mobileNo;

	private String gender;

	private LocalDate dob;

	private String ssn;

	private String activeSw;

	private String accStatus;

	private Integer roleId;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<PlanEntity> plans;

}
