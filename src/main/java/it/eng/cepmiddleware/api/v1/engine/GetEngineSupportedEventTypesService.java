package it.eng.cepmiddleware.api.v1.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.Service;
import it.eng.cepmiddleware.engine.CEPEngineFactory;

@org.springframework.stereotype.Service
public class GetEngineSupportedEventTypesService implements Service {

	@Autowired private CEPEngineFactory engineFactory;

	@Override
	public ResponseEntity<?> execute(Object... parameters) {
		if (parameters[0] instanceof String) {
			String engineId = (String) parameters[0];
			return getEngineSupportedEventTypes(engineId);
		}
		return Service.paramError;
	}

	private ResponseEntity<?> getEngineSupportedEventTypes(String engineId) {
		return engineFactory.getCEPEngine(engineId).getSupportedEventTypes();
	}

}
