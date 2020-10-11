package ua.conference.springapp.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{
	USER,
	SPEAKER,
	ADMIN;

	@Override
	public String getAuthority() {
		return name();
	}
}
