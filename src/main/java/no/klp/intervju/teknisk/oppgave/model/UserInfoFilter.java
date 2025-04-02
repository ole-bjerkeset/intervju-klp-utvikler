package no.klp.intervju.teknisk.oppgave.model;

import no.klp.intervju.teknisk.oppgave.enums.UserType;

public class UserInfoFilter {
	private String email;
	private UserType type;

	public String getEmail() {
		return email;
	}

	public UserInfoFilter setEmail(String email) {
		this.email = email;
		return this;
	}

	public UserType getType() {
		return type;
	}

	public UserInfoFilter setType(UserType type) {
		this.type = type;
		return this;
	}
}
