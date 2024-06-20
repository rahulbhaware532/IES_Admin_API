package com.jrtp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.jrtp.service.UserService;

@RestController
public class UserRestController {

	@Autowired
	private UserService userService;

//	@Autowired
//	private AccountService accService;

}
