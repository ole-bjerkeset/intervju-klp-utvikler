package no.klp.intervju.teknisk.oppgave.repository;

import no.klp.intervju.teknisk.oppgave.domain.UserInfoEntity;
import no.klp.intervju.teknisk.oppgave.model.UserInfoFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class UserInfoDAOService {

	private final UserInfoRepository userInfoRepository;
	private final UserInfoSpesification userInfoSpesification;

	public UserInfoDAOService(UserInfoRepository userInfoRepository,
							  UserInfoSpesification userInfoSpesification) {
		this.userInfoRepository = userInfoRepository;
		this.userInfoSpesification = userInfoSpesification;
	}

	public UserInfoEntity getUserInfoById(Long id) {
		return userInfoRepository.findById(id)
				.orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
	}

	@Transactional
	public UserInfoEntity save(UserInfoEntity userInfoEntity) {
		return userInfoRepository.save(userInfoEntity);
	}

	public List<UserInfoEntity> findAllByFilter(UserInfoFilter filter) {
		Specification<UserInfoEntity> specification = userInfoSpesification.findUserInfoBy(filter);
		return userInfoRepository.findAll(specification);
	}
}
