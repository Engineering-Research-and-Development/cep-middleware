package it.eng.cepmiddleware.rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Long> {
	
	@Query(value = "select r from Rule r where r.ruleId = ?1")
	Rule getRuleById(String ruleId);
	
}
