package it.eng.cepmiddleware.api.v1.engine.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.Service;
import it.eng.cepmiddleware.rule.RuleCRUDService;

@org.springframework.stereotype.Service
public class DeleteRuleService implements Service {

	@Autowired RuleCRUDService crudService;

	ResponseEntity<String> paramError = new ResponseEntity<String>(
		"Correct parameters not provided",
		HttpStatus.BAD_REQUEST
	);

	@Override
	public ResponseEntity<?> execute(Object... parameters) {
		if (parameters[0] instanceof String && parameters[1] instanceof String) {
			String engineId = (String) parameters[0];
			String ruleId = (String) parameters[1];
			return deleteRuleService(engineId, ruleId);
		}
		return paramError;
	}

	private ResponseEntity<?> deleteRuleService(String engineId, String ruleId) {
		try {
			crudService.delete(ruleId);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().build();
	}

}
