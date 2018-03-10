package com.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UiController {

	@RequestMapping("/")
	public String index() {
		System.out.println("*** UiController *** index ****");
		return "index";
	}

	@RequestMapping("/login")
	public String login() {
		System.out.println("*** UiController *** login ****");
		return "login";
	}

	@RequestMapping("/createAccount")
	public String createAccount() {
		System.out.println("*** UiController *** createAccount ****");
		return "createAccount";
	}

}