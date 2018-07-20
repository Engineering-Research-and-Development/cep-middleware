package it.eng.cepmiddleware.api.v1.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.Service;
import it.eng.cepmiddleware.engine.CEPEngine;
import it.eng.cepmiddleware.engine.EngineInfoTokenRepository;
import it.eng.cepmiddleware.responses.PlainResponseBody;

@org.springframework.stereotype.Service
public class DeleteEngineService implements Service {

	@Autowired private EngineInfoTokenRepository repository;
	ResponseEntity<String> paramError = new ResponseEntity<String>(
		"Correct parameters not provided",
		HttpStatus.BAD_REQUEST
	);

	@Override
	public ResponseEntity<?> execute(Object... parameters) {
		if (parameters[0] instanceof String) {
			String engineId = (String) parameters[0];
			return deleteEngine(engineId);
		}
		return paramError;
	}

	private ResponseEntity<?> deleteEngine(String engineId) {
		try {
			repository.deleteById(engineId);
		} catch (Exception e) {
			return new ResponseEntity(
				new PlainResponseBody(String.format("%s engine not found", engineId)),
				HttpStatus.NOT_FOUND
			);
		}
		return new ResponseEntity(
			new PlainResponseBody(String.format("%s engine deleted", engineId)),
			HttpStatus.OK
		);
	}

}
