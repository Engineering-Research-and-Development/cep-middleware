package it.eng.cepmiddleware.api.v1.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.Service;
import it.eng.cepmiddleware.config.CEPEngineConfiguration;

@org.springframework.stereotype.Service
public class GetEngineService implements Service {

	@Autowired CEPEngineConfiguration engineConfig;

	@Override
	public ResponseEntity<?> execute(Object... parameters) {
		if (parameters[0] instanceof String) {
			String engineId = (String) parameters[0];
			return getEngine(engineId);
		}
		return ResponseEntity.badRequest().build();
	}

	private ResponseEntity<?> getEngine(String engineId) {
		return new ResponseEntity<>(
			engineConfig.getEngineInfo(engineId),
			HttpStatus.OK
		);
	}

}
