package it.eng.cepmiddleware.engine.perseo_core;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.eng.cepmiddleware.CRUDService;
import it.eng.cepmiddleware.engine.CEPEngine;
import it.eng.cepmiddleware.engine.CEPEngineFactory;
import it.eng.cepmiddleware.rule.Rule;
import it.eng.cepmiddleware.rule.RuleRepository;

@Service
public class MiddlewarePerseoCoreRuleCRUDService implements CRUDService<Rule, String> {

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
	public void update(Rule rule) throws Exception {
		CEPEngine engine = engineFactory.getCEPEngine(rule.getOwner());
		if (engine.updateRule(rule).getStatusCode().is2xxSuccessful()) {
			repository.save(rule);
		} else {
			throw new Exception("Rule couldn't be updated");
		}
	}

	@Override
	public void delete(String ruleId) throws Exception {
		Rule rule = repository.getRuleById(ruleId);
		repository.delete(rule);
		engineFactory
			.getCEPEngine(rule.getOwner())
			.deleteRule(ruleId);
	}

}

