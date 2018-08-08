package it.eng.cepmiddleware.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import it.eng.cepmiddleware.engine.perseo.Perseo;

@Configuration
public class DefaultEngineProvider implements CEPEngineProvider {

	@Autowired private EngineInfoTokenRepository repository;
	ErrorCEPEngine errorEngine = new ErrorCEPEngine();
	
	@Override
	public CEPEngine getCEPEngine(String engineId) {
		return repository.findById(engineId).<CEPEngine>map(
			(token) -> cepEngine(token)
		).orElseGet(() -> errorEngine);
	}
	
	@Bean
	@Scope("request")
	CEPEngine cepEngine(EngineInfoToken token) {
		if (token.getEngineType().equals("Perseo")) {
			return new Perseo(
				token.getEngineId(),
				token.getHostUrl()
			);
		}
		return errorEngine;
	}

}
