package it.eng.cepmiddleware.api.v1.exposed_engine.rules;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ExposedEngineRulesController implements ExposedEngineRulesApi {

	@Autowired GetExposedEngineRuleService getRuleService;
	@Autowired GetExposedEngineRulesService getRulesService;
	@Autowired CreateExposedEngineRuleService createRuleService;
	@Autowired DeleteExposedEngineRuleService deleteRuleService;
	@Autowired UpdateExposedEngineRuleService updateRuleService;

	@Override
	public ResponseEntity<?> getRule(@PathVariable("ruleId") String ruleId) {
		return getRuleService.execute(ruleId);
	}

	@Override
	public ResponseEntity<?> getRules() {
		return getRulesService.execute();
	}

	@Override
	public ResponseEntity<?> createRule(@Valid @RequestBody Map<String, Object> rule) {
		return createRuleService.execute(rule);
	}

	@Override
	public ResponseEntity<?> deleteRule(@PathVariable("ruleId") String ruleId) {
		return deleteRuleService.execute(ruleId);
	}

	@Override
	public ResponseEntity<?> updateRule(
		@PathVariable("ruleId") String ruleId,
		@Valid Map<String, Object> rule
	) {
		return updateRuleService.execute(ruleId, rule);
	}

}
