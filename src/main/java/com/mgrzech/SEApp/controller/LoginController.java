package com.mgrzech.SEApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/customLoginPage")
	public String login() {
		
		return "plain-login";
	}
}
