package it.eng.cepmiddleware.api.v1.engine;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.Service;
import it.eng.cepmiddleware.config.CEPEngineConfiguration;
import it.eng.cepmiddleware.engine.CEPEngine;
import it.eng.cepmiddleware.config.EngineInfoToken;

@org.springframework.stereotype.Service
public class AddEngineService implements Service {
	
	@Autowired MapToEngineConverter converter;
	@Autowired CEPEngineConfiguration engineConfig;

	@Override
	public ResponseEntity<?> execute(Object... parameters) {
		if (parameters[0] instanceof EngineInfoToken) {
			EngineInfoToken engineInfo = (EngineInfoToken) parameters[0];
			return addEngine(engineInfo);
		}
		return Service.paramError;
	}

	private ResponseEntity<?> addEngine(EngineInfoToken engineInfo) {
		try {
			return new ResponseEntity<>(engineConfig.putCepEngine(engineInfo), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
