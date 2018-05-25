package it.eng.cepmiddleware.rule;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
		CEPEngine engine = engineFactory.getCEPEngine(rule.getOwner());
		if (/*engine.createRule(rule).getStatusCode().is2xxSuccessful()*/ true) {
			repository.save(rule);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rule update(Rule entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String entityId) {
		// TODO Auto-generated method stub
	}

}
