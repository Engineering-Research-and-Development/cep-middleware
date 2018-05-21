package it.eng.cepmiddleware.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Configuration;
import it.eng.cepmiddleware.CEPEngine;
import it.eng.cepmiddleware.PerseoCore;
import it.eng.cepmiddleware.PerseoFrontEnd;

@Configuration
public class CEPMiddlewareConfiguration {
	
	private Map<String, CEPEngine> cepEngines;
	
	public CEPMiddlewareConfiguration() {
		cepEngines = new HashMap<String, CEPEngine>();
		cepEngines.put("perseo-core", new PerseoCore("http://localhost:8080"));
		cepEngines.put("perseo-fe", new PerseoFrontEnd("http://localhost:9090"));
	}
	
	public CEPEngine getEngine(String id) {
		return cepEngines.get(id);
	}
}
