package it.eng.cepmiddleware.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RequestMatcher;

public class IdmRequestMatcher implements RequestMatcher {

	@Override
	public boolean matches(HttpServletRequest request) {
		return true;
	}

}
