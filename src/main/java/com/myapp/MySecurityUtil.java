package com.myapp;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MySecurityUtil {

	public String getLoggedinUserName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName(); // get logged in username
	}
}
