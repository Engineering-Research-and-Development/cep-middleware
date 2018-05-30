package it.eng.cepmiddleware.api.v1.engine.rules;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.Service;
import it.eng.cepmiddleware.engine.CEPEngineFactory;
import it.eng.cepmiddleware.rule.Rule;
import it.eng.cepmiddleware.rule.RuleCRUDService;

@org.springframework.stereotype.Service
public class UpdateRuleService implements Service {

	@Autowired RuleCRUDService ruleCRUDService;
	@Autowired CEPEngineFactory engineFactory;
	
	@Override
	public ResponseEntity<?> execute(Object... parameters) {
		if (
			parameters[0] instanceof String &&
			parameters[1] instanceof String &&
			parameters[2] instanceof Map
		) {
			String engineId = (String) parameters[0];
			String ruleId = (String) parameters[1];
			Map<String, Object> ruleMap = (Map<String, Object>) parameters[2];
			return updateRule(engineId, ruleId, ruleMap);
		}
		return ResponseEntity.badRequest().build();
	}

	private ResponseEntity<?> updateRule(String engineId, String ruleId, Map<String, Object> ruleMap) {
		Rule rule = engineFactory.getCEPEngine(engineId).getRuleConverter().convert(ruleMap);
		try {
			ruleCRUDService.update(rule);
		} catch (Exception e) {
			return new ResponseEntity<String>("Couldn't update the rule :(", HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok().build();
	}

}
