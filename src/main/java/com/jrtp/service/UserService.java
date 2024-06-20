package com.jrtp.service;

import com.jrtp.bindings.DashboardCard;
import com.jrtp.bindings.LoginForm;
import com.jrtp.bindings.UserAccountForm;

public interface UserService {

	public String login(LoginForm loginForm);

	public boolean recoverPwd(String email);

	public DashboardCard fetDashboardInfo();

	public UserAccountForm getUserByEmail(String email);

}
