package no.klp.intervju.teknisk.oppgave.resource.mapper;

import no.klp.intervju.teknisk.oppgave.model.UserInfoRequestDTO;
import no.klp.intervju.teknisk.oppgave.model.UserInfoResponseDTO;
import no.klp.intervju.teknisk.oppgave.domain.UserInfoEntity;
import org.springframework.stereotype.Component;

@Component
public class UserInfoMapper {

	public final UserInfoResponseDTO toDTO(UserInfoEntity source) {
		if (source == null) {
			return null;
		}
		return new UserInfoResponseDTO(
				source.getId().intValue(),
				source.getEmail(),
				source.getType()
		);
	}

	public final UserInfoEntity fromDTO(UserInfoRequestDTO source) {
		if (source == null) {
			return null;
		}
		return new UserInfoEntity().setEmail(source.email()).setType(source.type());
	}
}
