package it.eng.cepmiddleware.api.v1.engine;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.Service;
import it.eng.cepmiddleware.config.CEPEngineConfiguration;
import it.eng.cepmiddleware.engine.CEPEngine;

@org.springframework.stereotype.Service
public class AddEngineService implements Service {
	
	@Autowired MapToEngineConverter converter;
	@Autowired CEPEngineConfiguration engineConfig;

	@Override
	public ResponseEntity<?> execute(Object... parameters) {
		if (parameters[0] instanceof Map) {
			Map<String, String> engineInfo = (Map<String, String>)parameters[0];
			return addEngine(engineInfo);
		}
		return Service.paramError;
	}

	private ResponseEntity<?> addEngine(Map<String, String> engineInfo) {
		CEPEngine engine = converter.convert(engineInfo);
		if (engineConfig.getCepEngines().get(engine.getName()) == null) {
			engineConfig.getCepEngines().put(engine.getName(), engine);
			return ResponseEntity.ok().build();
		}
		return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	}

}
