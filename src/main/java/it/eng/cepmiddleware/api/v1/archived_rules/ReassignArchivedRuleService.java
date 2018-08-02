package it.eng.cepmiddleware.api.v1.archived_rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.ObjectToMapConverter;
import it.eng.cepmiddleware.Service;
import it.eng.cepmiddleware.engine.CEPEngineFactory;
import it.eng.cepmiddleware.responses.CepInclusiveResponseBody;
import it.eng.cepmiddleware.responses.PlainResponseBody;
import it.eng.cepmiddleware.rule.ArchivedRule;
import it.eng.cepmiddleware.rule.ArchivedRuleRepository;
import it.eng.cepmiddleware.rule.Rule;

@org.springframework.stereotype.Service
public class ReassignArchivedRuleService implements Service {

	@Autowired private ArchivedRuleRepository archivedRuleRepository;
	@Autowired private CEPEngineFactory engineFactory;

	@Override
	public ResponseEntity<?> execute(Object... parameters) {
		if (
			parameters[0] instanceof String &&
			parameters[1] instanceof ArchivedRuleReassignment
		) {
			String ruleId = (String) parameters[0];
			ArchivedRuleReassignment reassignment = (ArchivedRuleReassignment) parameters[1];
			return reemployArchivedRule(ruleId, reassignment);
		}
		return paramError;
	}

	private ResponseEntity<?> reemployArchivedRule(
		String ruleId,
		ArchivedRuleReassignment reassignment
	) {
		ArchivedRule archivedRule = archivedRuleRepository.getRuleById(ruleId);
		if (archivedRule == null) {
			return new ResponseEntity<>(
				new PlainResponseBody(
					"Archived Rule with the given ruleId, doesn't exist"
				),
				HttpStatus.NOT_FOUND
			);
		}
		Rule rule = archivedRule.extract();
		rule.setActive(reassignment.isActive());
		ResponseEntity ruleReassignment = engineFactory
			.getCEPEngine(reassignment.getNewOwner())
			.createRule(
				ObjectToMapConverter.convert(rule)
			)
		;
		if (ruleReassignment.getStatusCode().is2xxSuccessful()) {
			archivedRuleRepository.delete(archivedRule);
			return ResponseEntity.ok(
				new PlainResponseBody(
					"Rule successfully reassigned"
				)
			);
		} else {
			return new ResponseEntity<>(
				new CepInclusiveResponseBody(
					"Error reassigning archived rule",
					ruleReassignment.getBody().toString()
				),
				ruleReassignment.getStatusCode()
			);
		}
	}

}
