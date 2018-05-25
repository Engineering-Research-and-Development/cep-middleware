package it.eng.cepmiddleware.api.v1.engine.rules;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class EngineRulesController implements EngineRulesApi {

	@Autowired GetRuleService getRuleService;
	@Autowired CreateRuleService createRuleService;
	
	@Override
	public ResponseEntity<?> getRule(
		@PathVariable("engineId") String engineId,
		@PathVariable("ruleId") String ruleId
	) {
		return getRuleService.execute(engineId, ruleId);
	}
	
	@Override
	public ResponseEntity<?> getRules(@PathVariable("engineId") String engineId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> createRule(
		@PathVariable("engineId") String engineId,
		@Valid @RequestBody Map<String, Object> rule
	) {
		return createRuleService.execute(engineId, rule);
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
