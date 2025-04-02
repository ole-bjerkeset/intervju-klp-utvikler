package no.klp.intervju.teknisk.oppgave.resource.mapper;

import no.klp.intervju.teknisk.oppgave.model.CountyResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class KartverketMapper {

	public String toCountyCode(CountyResponseDTO county) {
		return county.fylkesnavn();
	}
}
