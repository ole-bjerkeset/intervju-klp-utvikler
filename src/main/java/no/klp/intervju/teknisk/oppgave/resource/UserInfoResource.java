package no.klp.intervju.teknisk.oppgave.resource;

import no.klp.intervju.teknisk.oppgave.integration.KartverketConsumer;
import no.klp.intervju.teknisk.oppgave.model.CountyResponseDTO;
import no.klp.intervju.teknisk.oppgave.model.UserInfoFilter;
import no.klp.intervju.teknisk.oppgave.model.UserInfoRequestDTO;
import no.klp.intervju.teknisk.oppgave.model.UserInfoResponseDTO;
import no.klp.intervju.teknisk.oppgave.domain.UserInfoEntity;
import no.klp.intervju.teknisk.oppgave.repository.UserInfoDAOService;
import jakarta.validation.constraints.NotNull;
import no.klp.intervju.teknisk.oppgave.resource.mapper.KartverketMapper;
import no.klp.intervju.teknisk.oppgave.resource.mapper.UserInfoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class UserInfoResource {

	private final UserInfoDAOService userInfoDAOService;
	private final UserInfoMapper userInfoMapper;
	private final KartverketConsumer kartverketConsumer;
	private final KartverketMapper kartverketMapper;

	public UserInfoResource(UserInfoDAOService userInfoDAOService,
							UserInfoMapper userInfoMapper,
							KartverketConsumer kartverketConsumer,
							KartverketMapper kartverketMapper) {
		this.userInfoDAOService = userInfoDAOService;
		this.userInfoMapper = userInfoMapper;
		this.kartverketConsumer = kartverketConsumer;
		this.kartverketMapper = kartverketMapper;
	}

	// Endpoints dedicated to fetching or creating user information.

	@GetMapping("/user/{id}")
	ResponseEntity<UserInfoResponseDTO> getUserInfo(@PathVariable("id") @NotNull Long id) {
		UserInfoEntity userInfoEntity = userInfoDAOService.getUserInfoById(id);

		return ResponseEntity.ok(userInfoMapper.toDTO(userInfoEntity));
	}

	@GetMapping("/user")
	ResponseEntity<List<UserInfoResponseDTO>> getAllUserInfoByParams(UserInfoFilter filter) {
		List<UserInfoEntity> userInfoEntities = userInfoDAOService.findAllByFilter(filter);
		List<UserInfoResponseDTO> responseList = userInfoEntities.stream()
				.map(userInfoMapper::toDTO)
				.toList();

		return ResponseEntity.ok(responseList);
	}

	@PostMapping("/user")
	ResponseEntity<UserInfoResponseDTO> createUserInfo(@RequestBody UserInfoRequestDTO request) {
		UserInfoEntity userInfoEntity = userInfoMapper.fromDTO(request);
		UserInfoEntity savedEntity = userInfoDAOService.save(userInfoEntity);

		return ResponseEntity.ok(userInfoMapper.toDTO(savedEntity));
	}

	// Endpoint for fetching map data
	@GetMapping("/county/{countyNumber}")
	ResponseEntity<String> getCounty(@PathVariable String countyNumber) {
		CountyResponseDTO responseFromKartverket = kartverketConsumer.getCountyName(countyNumber);

		return ResponseEntity.ok(kartverketMapper.toCountyCode(responseFromKartverket));
	}
}
