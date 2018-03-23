package com.myapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UiController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UiController.class);
	
	@RequestMapping("/")
	public String index() {
		LOGGER.info("*** UiController *** index ****");
		return "index";
	}

	@RequestMapping("/login")
	public String login() {
		LOGGER.info("*** UiController *** login ****");
		return "login";
	}

	@RequestMapping("/createAccount")
	public String createAccount() {
		LOGGER.info("*** UiController *** createAccount ****");
		return "createAccount";
	}

}