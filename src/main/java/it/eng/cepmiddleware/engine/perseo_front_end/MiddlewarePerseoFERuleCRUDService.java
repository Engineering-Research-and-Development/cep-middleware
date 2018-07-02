package it.eng.cepmiddleware.engine.perseo_front_end;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.eng.cepmiddleware.CRUDService;
import it.eng.cepmiddleware.engine.CEPEngine;
import it.eng.cepmiddleware.engine.CEPEngineFactory;
import it.eng.cepmiddleware.rule.PerseoFERule;
import it.eng.cepmiddleware.rule.PerseoFERuleRepository;
import it.eng.cepmiddleware.rule.Rule;
import it.eng.cepmiddleware.rule.RuleRepository;

@Service
public class MiddlewarePerseoFERuleCRUDService implements CRUDService<PerseoFERule, String> {

	@Autowired CEPEngineFactory engineFactory;
	@Autowired PerseoFERuleRepository repository;

	@Override
	public void create(PerseoFERule rule) throws Exception {
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
	public Optional<PerseoFERule> read(String ruleId) {
		PerseoFERule rule = repository.getRuleById(ruleId);
		return Optional.ofNullable(repository.getRuleByName(rule.getName()));
	}

	@Override
	public Collection<PerseoFERule> read() {
		return repository.findAll();
	}

	@Override
	public void update(PerseoFERule rule) throws Exception {
		CEPEngine engine = engineFactory.getCEPEngine(rule.getOwner());
		boolean isSameName = false;
		try {
			engine.getRule(rule.getName());
			isSameName = true;
		} catch (Exception e) {
			isSameName = false;
		}
		if (
			isSameName &&
			engine.updateRule(rule).getStatusCode().is2xxSuccessful()
		) {
			repository.save(rule);
		} else if (!isSameName) {
			String nameOfStoredRule = repository.getRuleById(rule.getRuleId()).getName();
			if (engine.createRule(rule).getStatusCode().is2xxSuccessful()) {
				repository.save(rule);
				engine.deleteRule(nameOfStoredRule);
			} else {
				throw new Exception("Provided Rule is not valid or the name already exists");
			}
		} else {
			throw new Exception("Rule couldn't be updated");
		}
	}

	@Override
	public void delete(String ruleId) throws Exception {
		PerseoFERule rule = repository.getRuleById(ruleId);
		repository.delete(rule);
		engineFactory
			.getCEPEngine(rule.getOwner())
			.deleteRule(rule.getName());
	}

}
