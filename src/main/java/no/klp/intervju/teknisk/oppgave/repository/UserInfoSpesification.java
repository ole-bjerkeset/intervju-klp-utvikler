package no.klp.intervju.teknisk.oppgave.repository;

import jakarta.persistence.criteria.Predicate;
import no.klp.intervju.teknisk.oppgave.domain.UserInfoEntity;
import no.klp.intervju.teknisk.oppgave.model.UserInfoFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserInfoSpesification {
	public Specification<UserInfoEntity> findUserInfoBy(UserInfoFilter filter) {
		return (root, criteriaQuery, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();

			// Decision
			if (filter.getEmail() != null) {
				predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("email"), filter.getEmail())));
			}
			if (filter.getType() != null) {
				predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("type"), filter.getType())));
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
