package it.eng.cepmiddleware.rule;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PerseoRuleRepository extends RuleRepository<PerseoRule> {

	@Query(value = "select r from PerseoRule r where r.ruleId = ?1")
	PerseoRule getRuleById(String ruleId);

	@Query(value = "select r from PerseoRule r where r.name = ?1")
	PerseoRule getRuleByName(String ruleName);

	@Query(value = "select r.ruleId from PerseoRule r where r.owner = ?1")
	Collection<String> getAllRuleIdsOfOwner(String owner);

}
