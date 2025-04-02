package no.klp.intervju.teknisk.oppgave.integration;

import no.klp.intervju.teknisk.oppgave.model.CountyResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class KartverketConsumer {

	public CountyResponseDTO getCountyName(String countyNumber) {
		String endpoint = "https://api.kartverket.no/kommuneinfo/v1/fylker/" + countyNumber;
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(endpoint, CountyResponseDTO.class);
	}

}
