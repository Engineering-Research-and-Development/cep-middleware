package it.eng.cepmiddleware.rule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PerseoFERuleRepository extends JpaRepository<PerseoFERule, Long> {

	@Query(value = "select r from PerseoFERule r where r.ruleId = ?1")
	PerseoFERule getRuleById(String ruleId);

	@Query(value = "select r from PerseoFERule r where r.name = ?1")
	PerseoFERule getRuleByName(String ruleName);

}
