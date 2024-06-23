package com.jrtp.rest;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jrtp.bindings.UserAccountForm;
import com.jrtp.service.AccountService;

@RestController
public class AccountRestController {

	@Autowired
	private AccountService accountService;

	private Logger logger = org.slf4j.LoggerFactory.getLogger(AccountRestController.class);

	@PostMapping("/user")
	public ResponseEntity<String> creatAccount(@RequestBody UserAccountForm userAccountForm) {
		logger.debug("Account Creation Process Started");
		boolean status = accountService.createUserAccount(userAccountForm);
		logger.debug("Account Creation Process Completed..");

		if (status) {
			logger.info("Account Created Successfully...");

			return new ResponseEntity<String>("Account Created", HttpStatus.CREATED);
		} else {
			logger.info("Account Creation Failed ..");
			return new ResponseEntity<String>("Account Creation Failed", HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@GetMapping("/users")
	public ResponseEntity<List<UserAccountForm>> getUsers() {
		logger.debug("Fetching User Accounts process started....");

		List<UserAccountForm> userAcounts = accountService.fetchUserAcounts();
		logger.debug("Fetching User Accounts process completed....");
		logger.info("User Accounts Fetched Success..");

		return new ResponseEntity<>(userAcounts, HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<UserAccountForm> getUser(@PathVariable("userId") Long userId) {
		UserAccountForm userAccById = accountService.getUserAccById(userId);
		logger.info("User Accounts Fetched Successfully..");

		return new ResponseEntity<>(userAccById, HttpStatus.OK);

	}

	@PutMapping("/user/{userId}/{status}")
	public ResponseEntity<List<UserAccountForm>> updateUserAcc(@PathVariable("userId") Long userId,
			@PathVariable("status") String status) {
		logger.debug(" User Accounts update process start....");

		accountService.changeAccStatus(userId, status);
		logger.info("User Accounts status  updated successfully..");

		List<UserAccountForm> userAcounts = accountService.fetchUserAcounts();

		return new ResponseEntity<>(userAcounts, HttpStatus.OK);
	}

}
