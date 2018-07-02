package it.eng.cepmiddleware.api.v1.engine.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.Service;
import it.eng.cepmiddleware.engine.CEPEngineFactory;
import it.eng.cepmiddleware.engine.ErrorCEPEngine;
import it.eng.cepmiddleware.rule.RuleCRUDService;

@org.springframework.stereotype.Service
public class GetRulesService implements Service {

	@Autowired CEPEngineFactory engineFactory;

	ResponseEntity<String> paramError = new ResponseEntity<String>(
		"Correct parameters not provided",
		HttpStatus.BAD_REQUEST
	);

	@Override
	public ResponseEntity<?> execute(Object... parameters) {
		if (parameters[0] instanceof String) {
			String engineId = (String) parameters[0];
			return getRulesService(engineId);
		}
		return paramError;
	}

	private ResponseEntity<?> getRulesService(String engineId) {
		if (!(engineFactory.getCEPEngine(engineId) instanceof ErrorCEPEngine)) {
			try {
				return new ResponseEntity<>(
					engineFactory
						.getCEPEngine(engineId)
						.getMiddlewareCRUD()
						.read()
						.stream()
						.filter(
							(rule) -> rule.getOwner().equals(engineId)
						).toArray()
					,
					HttpStatus.OK
				);
			} catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}
		} else return new ResponseEntity<>("CEP engine doesn't exist", HttpStatus.NOT_FOUND);
	}

}
