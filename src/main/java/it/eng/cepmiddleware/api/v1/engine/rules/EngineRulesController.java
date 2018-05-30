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
	@Autowired GetRulesService getRulesService;
	@Autowired DeleteRuleService deleteRuleService;
	@Autowired UpdateRuleService updateRuleService;
	@Autowired RuleIsEnabledService ruleIsEnabledService;
	@Autowired ToggleRuleService toggleRuleService;
	
	@Override
	public ResponseEntity<?> getRule(
		@PathVariable("engineId") String engineId,
		@PathVariable("ruleId") String ruleId
	) {
		return getRuleService.execute(engineId, ruleId);
	}
	
	@Override
	public ResponseEntity<?> getRules(@PathVariable("engineId") String engineId) {
		return getRulesService.execute(engineId);
	}

	@Override
	public ResponseEntity<?> createRule(
		@PathVariable("engineId") String engineId,
		@Valid @RequestBody Map<String, Object> rule
	) {
		return createRuleService.execute(engineId, rule);
	}

	@Override
	public ResponseEntity<?> deleteRule(
		@PathVariable("engineId") String engineId,
		@PathVariable("ruleId") String ruleId
	) {
		return deleteRuleService.execute(engineId, ruleId);
	}

	@Override
	public ResponseEntity<?> updateRule(
		@PathVariable("engineId") String engineId,
		@PathVariable("ruleId") String ruleId,
		@Valid @RequestBody Map<String, Object> rule
	) {
		return updateRuleService.execute(engineId, ruleId, rule);
	}

	@Override
	public ResponseEntity<?> ruleIsEnabled(String engineId, String ruleId) {
		return ruleIsEnabledService.execute(engineId, ruleId);
	}

	@Override
	public ResponseEntity<?> toggleRule(String engineId, String ruleId, @Valid Boolean enableSwitch) {
		return toggleRuleService.execute(engineId, ruleId, enableSwitch);
	}

}
