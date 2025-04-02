package no.klp.intervju.teknisk.oppgave.model;

import no.klp.intervju.teknisk.oppgave.enums.UserType;

public record UserInfoResponseDTO(
		Integer id,
		String email,
		UserType type
) {
}

