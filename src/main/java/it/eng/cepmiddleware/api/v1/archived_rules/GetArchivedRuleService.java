package it.eng.cepmiddleware.api.v1.archived_rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.Service;
import it.eng.cepmiddleware.responses.PlainResponseBody;
import it.eng.cepmiddleware.rule.ArchivedRuleRepository;

@org.springframework.stereotype.Service
public class GetArchivedRuleService implements Service {

	@Autowired private ArchivedRuleRepository archivedRuleRepository;

	@Override
	public ResponseEntity<?> execute(Object... parameters) {
		if (parameters[0] instanceof String) {
			String ruleId = (String) parameters[0];
			return getArchivedRule(ruleId);
		}
		return paramError;
	}

	private ResponseEntity<?> getArchivedRule(String ruleId) {
		Object archivedRule = archivedRuleRepository.getRuleById(ruleId);
		if (archivedRule == null) {
			return new ResponseEntity(
				new PlainResponseBody("Rule with the given ruleId doesn't exist"),
				HttpStatus.NOT_FOUND
			);
		}
		return ResponseEntity.ok(archivedRule);
	}

}
