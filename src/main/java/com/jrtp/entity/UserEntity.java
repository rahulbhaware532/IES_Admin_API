package com.jrtp.entity;

import java.time.LocalDate;

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

@Getter
@Setter
@Entity
@Table(name = "IES_USERS")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "FULL_NAME", nullable = false)
	private String fullName;

	@Column(name = "USER_EMAIL", nullable = false, unique = true)
	private String userEmail;

	@Column(name = "USER_PWD", nullable = false)
	private String userPwd;

	@Column(name = "USER_PHNO")
	private String userPhno;

	@Column(name = "USER_GENDER")
	private String userGender;

	@Column(name = "USER_DOB")
	private LocalDate userDob;

	@Column(name = "USER_SSN", unique = true)
	private String userSsn;

	@Column(name = "ACTIVE_SW", nullable = false, columnDefinition = "char(1) default 'ACTIVE'")
	private String activeSw = "ACTIVE";

	@Column(name = "ACC_STATUS", nullable = false, columnDefinition = "varchar(20) default 'LOCKED'")
	private String accStatus = "LOCKED";

	@Column(name = "ROLE_ID", nullable = false)
	private Integer roleId;

	@Column(name = "CREATE_DATE", nullable = false)
	private LocalDate createDate;

	@Column(name = "UPDATE_DATE")
	private LocalDate updateDate;

	@ManyToOne
	@JoinColumn(name = "CREATED_BY", referencedColumnName = "USER_ID")
	private UserEntity createdBy;

	@ManyToOne
	@JoinColumn(name = "UPDATED_BY", referencedColumnName = "USER_ID")
	private UserEntity updatedBy;

}
