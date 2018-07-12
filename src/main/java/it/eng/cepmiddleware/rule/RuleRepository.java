package it.eng.cepmiddleware.rule;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository<T extends Rule> extends JpaRepository<T, Long> {

	@Query(value = "select r from Rule r where r.ruleId = ?1")
	T getRuleById(String ruleId);

	@Query(value = "select r from Rule r where r.owner = ?1")
	Collection<T> getRulesByOwner(String owner);

}
