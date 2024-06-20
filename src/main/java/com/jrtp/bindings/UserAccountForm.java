package com.jrtp.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserAccountForm {

	private Long userId;

	private String fullName;

	private String email;

	private Long mobileNo;

	private String gender;

	private LocalDate dob;

	private Long ssn;

	private String activeSw;

	private Integer roleId;

}
