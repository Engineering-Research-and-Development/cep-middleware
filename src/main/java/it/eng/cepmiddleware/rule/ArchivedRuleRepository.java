package it.eng.cepmiddleware.rule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchivedRuleRepository<T extends ArchivedRule> extends JpaRepository<T, Long> {

	@Query(value = "select r from ArchivedRule r where r.ruleId = ?1")
	T getRuleById(String ruleId);

}
