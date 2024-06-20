package com.jrtp.bindings;

import lombok.Data;

@Data
public class DashboardCard {

	private Long plansCNT;

	private Long approvedCnt;

	private Long deniedCnt;

	private Double benefitAmtGiven;

	private UserAccountForm user;

}
