package com.myapp;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

/**
 * CSRF Filter
 * 
 * @author Tirumurugan
 */
public class MyCsrfHeaderFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyCsrfHeaderFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		LOGGER.info("******************** MyCsrfHeaderFilter *******************");
		CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
		if (csrf != null) {

			Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");

			String token = csrf.getToken();
			if (cookie == null || (token != null && !token.equals(cookie.getValue()))) {

				cookie = new Cookie("XSRF-TOKEN", token);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
		filterChain.doFilter(request, response);
	}
}
