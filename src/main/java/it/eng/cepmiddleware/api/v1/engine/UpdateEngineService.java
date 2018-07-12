package it.eng.cepmiddleware.api.v1.engine;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.Service;
import it.eng.cepmiddleware.config.CEPEngineConfiguration;
import it.eng.cepmiddleware.config.EngineInfoToken;
import it.eng.cepmiddleware.engine.CEPEngine;

@org.springframework.stereotype.Service
public class UpdateEngineService implements Service {
	
	@Autowired MapToEngineConverter converter;
	@Autowired CEPEngineConfiguration engineConfig;

	@Override
	public ResponseEntity<?> execute(Object... parameters) {
		if (
			parameters[0] instanceof String &&
			parameters[1] instanceof EngineInfoToken
		) {
			String engineId = (String) parameters[0];
			EngineInfoToken engineInfo = (EngineInfoToken) parameters[1];
			return updateEngine(engineId, engineInfo);
		}
		return Service.paramError;
	}

	private ResponseEntity<?> updateEngine(String engineId, EngineInfoToken engineInfo) {
		return new ResponseEntity("Not implemented yet", HttpStatus.NOT_IMPLEMENTED);
	}

}
