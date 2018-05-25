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

	@Override
	public ResponseEntity<?> getRule(String engineId, String ruleId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ResponseEntity<?> getRules(@PathVariable("engineId") String engineId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> createRule(String engineId, @Valid String rule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> deleteRule(String engineId, String ruleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> updateRule(String engineId, String ruleId, @Valid String rule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> ruleIsEnabled(String engineId, String ruleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> toggleRule(String engineId, String ruleId, @Valid Boolean enableSwitch) {
		// TODO Auto-generated method stub
		return null;
	}

}
