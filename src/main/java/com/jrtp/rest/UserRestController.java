package com.jrtp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jrtp.bindings.DashboardCard;
import com.jrtp.bindings.LoginForm;
import com.jrtp.bindings.UserAccountForm;
import com.jrtp.service.UserService;

@RestController
public class UserRestController {

	@Autowired
	private UserService userService;

//	@Autowired
//	private AccountService accService;

	@PostMapping("/login")
	public String login(@RequestBody LoginForm loginForm) {
		String status = userService.login(loginForm);
		if (status.equals("success")) {
			return "redirect:/dashboard?email=" + loginForm.getEmail();
		} else {
			return status;
		}
	}

	@GetMapping("/dashboard")
	public ResponseEntity<DashboardCard> buildDashboard(@RequestParam("email") String email) {
		UserAccountForm user = userService.getUserByEmail(email);
		DashboardCard dashboardCard = userService.fetDashboardInfo();
		dashboardCard.setUser(user);
		return new ResponseEntity<>(dashboardCard, HttpStatus.OK);
	}

}
