package it.eng.cepmiddleware.api.v1.exposed_engine.rules;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public class ExposedEngineRulesController implements ExposedEngineRulesApi {

	@Override
	public ResponseEntity<?> getRule(@PathVariable("ruleId") String ruleId) {
		return null;
	}

	@Override
	public ResponseEntity<?> getRules() {
		return null;
	}

	@Override
	public ResponseEntity<?> createRule(@Valid @RequestBody Map<String, Object> rule) {
		return null;
	}

	@Override
	public ResponseEntity<?> deleteRule(@PathVariable("ruleId") String ruleId) {
		return null;
	}

	@Override
	public ResponseEntity<?> updateRule(
		@PathVariable("ruleId") String ruleId,
		@Valid Map<String, Object> rule
	) {
		return null;
	}

}
