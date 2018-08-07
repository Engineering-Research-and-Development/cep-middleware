package it.eng.cepmiddleware.security;

import org.springframework.security.core.GrantedAuthority;

public class IdmGrantedAuthority implements GrantedAuthority {

	private String role;

	IdmGrantedAuthority(String role) {
		this.role = "ROLE_" + role;
	}

	@Override
	public String getAuthority() {
		return this.role;
	}

	@Override
	public String toString() {
		return "IdmGrantedAuthority [role=" + role + "]";
	}

}
