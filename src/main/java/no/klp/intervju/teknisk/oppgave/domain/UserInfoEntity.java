package no.klp.intervju.teknisk.oppgave.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import no.klp.intervju.teknisk.oppgave.enums.UserType;

@Entity
public class UserInfoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	@Enumerated(EnumType.STRING)
	private UserType type;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public UserInfoEntity setEmail(String email) {
		this.email = email;
		return this;
	}

	public UserType getType() {
		return type;
	}

	public UserInfoEntity setType(UserType type) {
		this.type = type;
		return this;
	}
}
