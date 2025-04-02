package no.klp.intervju.teknisk.oppgave.repository;

import no.klp.intervju.teknisk.oppgave.domain.UserInfoEntity;
import no.klp.intervju.teknisk.oppgave.enums.UserType;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @see <a href="https://jira.klp.no/browse/NPPMOD-4315">NPPMOD-4315</a>
 */
@Repository
public interface UserInfoRepository extends CrudRepository<UserInfoEntity, Long>, JpaSpecificationExecutor<UserInfoEntity> {

	public List<UserInfoEntity> findAllByEmailOrType(String email, UserType type);
}