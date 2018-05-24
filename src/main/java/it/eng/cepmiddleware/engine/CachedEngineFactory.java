package it.eng.cepmiddleware.engine;

import org.springframework.stereotype.Component;

import it.eng.cepmiddleware.config.CEPMiddlewareConfiguration;

@Component
public class CachedEngineFactory implements CEPEngineFactory {

	private CEPMiddlewareConfiguration config;
	
	CachedEngineFactory() {
		config = new CEPMiddlewareConfiguration();
	}
	
	@Override
	public CEPEngine getCEPEngine(String engineId) {
		return config.getEngine(engineId);
	}

}
