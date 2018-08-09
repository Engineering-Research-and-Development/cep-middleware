package it.eng.cepmiddleware.security;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import it.eng.cepmiddleware.config.CEPMiddlewareConfiguration;

public class IdmAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	IdmAuthenticationFilter() {
		this(new IdmRequestMatcher());
	}

	protected IdmAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
		super(requiresAuthenticationRequestMatcher);
	}

	@Override
	public Authentication attemptAuthentication(
		HttpServletRequest request,
		HttpServletResponse response
	) throws AuthenticationException {
		String principal;
		String XAuthToken = request.getHeader("x-auth-token");
		ArrayList<IdmGrantedAuthority> roles = new ArrayList<>();
		String idmHostUrl = CEPMiddlewareConfiguration.getIdmHostUrl();
		if (XAuthToken == null) {
			throw new AuthenticationException("Please provide the x-auth-token header"){};
		}
		try {
			HttpResponse<JsonNode> userInfoResponse = Unirest
				.get(idmHostUrl +"/user?access_token=" + XAuthToken)
				.asJson();
			if (userInfoResponse.getStatus()/100 != 2) {
				throw new AuthenticationException("x-auth-token is invalid, or expired"){};
			}
			principal = userInfoResponse.getBody().getObject().getString("id");
			JSONArray roleArray = userInfoResponse
				.getBody()
				.getObject()
				.getJSONArray("roles")
			;
			int roleArrayLength = roleArray.length();
			for (int i = 0;i<roleArrayLength; i++) {
				roles.add(
					new IdmGrantedAuthority(
						roleArray.getJSONObject(i).getString("name")
					)
				);
			}
		} catch (UnirestException | JSONException e) {
			throw new AuthenticationException(e.getMessage()) {
				private static final long serialVersionUID = 1L;
			};
		}
		return new UsernamePasswordAuthenticationToken(principal, XAuthToken, roles);
	}

	protected void successfulAuthentication(
		javax.servlet.http.HttpServletRequest request,
        javax.servlet.http.HttpServletResponse response,
        javax.servlet.FilterChain chain,
        Authentication authResult
    ) throws java.io.IOException, javax.servlet.ServletException {
		SecurityContextHolder.getContext().setAuthentication(authResult);
		chain.doFilter(request, response);
	}

}
