package it.eng.cepmiddleware.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.eng.cepmiddleware.config.CEPEngineConfiguration;

@Component
public class CachedEngineFactory implements CEPEngineFactory {

	@Autowired private CEPEngineConfiguration config;
	ErrorCEPEngine errorEngine = new ErrorCEPEngine();
	
	@Override
	public CEPEngine getCEPEngine(String engineId) {
		return config.getCepEngine(engineId).orElse(errorEngine);
	}

}
