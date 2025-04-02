package no.klp.intervju.teknisk.oppgave.model;

import java.util.List;

public record CountyResponseDTO(
		Avgrensningsboks avgrensningsboks,
		String fylkesnavn,
		String fylkesnummer,
		List<Kommune> kommuner
) {
	public record Avgrensningsboks(
			List<List<List<Integer>>> coordinates,
			Crs crs,
			String type
	) {
		public record Crs(
				Properties properties,
				String type
		) {
			public record Properties(
					String name
			) {
			}
		}
	}

	public record Kommune(
			String kommunenavn,
			String kommunenummer
	) {
	}
}