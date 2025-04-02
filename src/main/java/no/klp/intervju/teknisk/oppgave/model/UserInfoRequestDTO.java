package no.klp.intervju.teknisk.oppgave.model;

import no.klp.intervju.teknisk.oppgave.enums.UserType;

public record UserInfoRequestDTO(
		String email,
		UserType type
) {
}
