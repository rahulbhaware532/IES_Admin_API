package com.jrtp.service;

import java.util.List;

import com.jrtp.bindings.UnlockAccForm;
import com.jrtp.bindings.UserAccountForm;

public interface AccountService {

	public boolean createUserAccount(UserAccountForm accForm);

	public List<UserAccountForm> fetchUserAcounts();

	public UserAccountForm getUserAccById(Long id);

	public String changeAccStatus(Long userId, String status);

	String unlockUserAccount(UnlockAccForm unlockAccForm);

}
