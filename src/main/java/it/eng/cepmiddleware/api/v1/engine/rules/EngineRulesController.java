package it.eng.cepmiddleware.api.v1.engine.rules;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import it.eng.cepmiddleware.engine.CEPEngine;
import it.eng.cepmiddleware.engine.CEPEngineFactory;
import it.eng.cepmiddleware.rule.PerseoFERule;
import it.eng.cepmiddleware.rule.PerseoFERuleRepository;
import it.eng.cepmiddleware.rule.Rule;

@Controller
public class EngineRulesController implements EngineRulesApi {
	
	@Autowired
	CEPEngineFactory engineFactory;
	
	@Autowired
	PerseoFERuleRepository repo;

	@Override
	public ResponseEntity<?> getRules(@PathVariable("engineId") String engineId) {
		return null;
	}

	@Override
	public ResponseEntity<Void> enginesEngineIdRulesPost(String engineId, @Valid String rule) {
		return null;
	}

	@Override
	public ResponseEntity<Void> enginesEngineIdRulesRuleIdDelete(String engineId, String ruleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> enginesEngineIdRulesRuleIdEnabledGet(String engineId, String ruleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> enginesEngineIdRulesRuleIdEnabledPut(String engineId, String ruleId,
			@Valid Boolean enableSwitch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> enginesEngineIdRulesRuleIdGet(String engineId, String ruleId) {
		System.out.println(engineId);
		return null;
	}

	@Override
	public ResponseEntity<Void> enginesEngineIdRulesRuleIdPut(String engineId, String ruleId, @Valid String rule) {
		// TODO Auto-generated method stub
		return null;
	}

}
