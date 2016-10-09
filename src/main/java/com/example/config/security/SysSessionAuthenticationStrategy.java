package com.example.config.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

public class SysSessionAuthenticationStrategy implements SessionAuthenticationStrategy {

	@Override
	public void onAuthentication(Authentication arg0, HttpServletRequest arg1,
			HttpServletResponse arg2) throws SessionAuthenticationException {
	}

}
