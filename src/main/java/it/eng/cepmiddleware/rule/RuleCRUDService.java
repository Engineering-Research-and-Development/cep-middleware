package it.eng.cepmiddleware.rule;

import java.util.Collection;
import java.util.Optional;

import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.eng.cepmiddleware.CRUDService;
import it.eng.cepmiddleware.engine.CEPEngine;
import it.eng.cepmiddleware.engine.CEPEngineFactory;

@Service
public class RuleCRUDService implements CRUDService<Rule, String> {

	@Autowired CEPEngineFactory engineFactory;
	@Autowired RuleRepository repository;

	@Override
	public void create(Rule rule) throws Exception {
		rule.setRuleId(Rule.defaultRuleId);
		CEPEngine engine = engineFactory.getCEPEngine(rule.getOwner());
		if (engine.createRule(rule).getStatusCode().is2xxSuccessful()) {
			repository.save(rule);
			engine.deleteRule(Rule.defaultRuleId);
			engine.createRule(rule);
		} else {
			throw new Exception(String.format("The %s engine couldn't save your rule", rule.getOwner()));
		}
	}

	@Override
	public Optional<Rule> read(String ruleId) {
		return Optional.ofNullable(repository.getRuleById(ruleId));
	}

	@Override
	public Collection<Rule> read() {
		return repository.findAll();
	}

	@Override
	public void update(Rule rule) {
		CEPEngine engine = engineFactory.getCEPEngine(rule.getOwner());
		if (engine.updateRule(rule).getStatusCode().is2xxSuccessful()) {
			repository.save(rule);
		}
	}

	@Override
	public void delete(String ruleId) throws Exception {
		Rule rule = repository.getRuleById(ruleId);
		ResponseEntity<?> deleteResponse = engineFactory
			.getCEPEngine(rule.getOwner())
			.deleteRule(ruleId);
		if (deleteResponse.getStatusCode().is2xxSuccessful()) {
			repository.delete(rule);
		} else {
			throw new Exception("Rule couldn't be deleted");
		}
	}

}
