package it.eng.cepmiddleware.api.v1.engines.rules;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.Service;
import it.eng.cepmiddleware.engine.CEPEngineProvider;

@org.springframework.stereotype.Service
public class CreateRuleService implements Service {

	@Autowired private CEPEngineProvider engineProvider;

	@Override
	public ResponseEntity<?> execute(Object... parameters) {
		if (parameters[0] instanceof String && parameters[1] instanceof Map) {
			String engineId = (String) parameters[0];
			Map<String, Object> ruleMap = (Map<String, Object>) parameters[1];
			return createRule(engineId, ruleMap);
		}
		return ResponseEntity.badRequest().build();
	}

	private ResponseEntity<?> createRule(String engineId, Map<String, Object> ruleMap) {
		return engineProvider.getCEPEngine(engineId).createRule(ruleMap);
	}

}
