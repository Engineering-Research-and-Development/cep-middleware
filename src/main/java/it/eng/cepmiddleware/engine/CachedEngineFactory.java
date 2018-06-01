package it.eng.cepmiddleware.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.eng.cepmiddleware.config.CEPMiddlewareConfiguration;

@Component
public class CachedEngineFactory implements CEPEngineFactory {

	@Autowired private CEPMiddlewareConfiguration config;
	
	@Override
	public CEPEngine getCEPEngine(String engineId) {
		return config.getEngine(engineId);
	}

}
