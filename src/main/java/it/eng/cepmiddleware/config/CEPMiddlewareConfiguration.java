package it.eng.cepmiddleware.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Configuration;

import it.eng.cepmiddleware.engine.CEPEngine;
import it.eng.cepmiddleware.engine.ErrorCEPEngine;
import it.eng.cepmiddleware.engine.PerseoCore;
import it.eng.cepmiddleware.engine.PerseoFrontEnd;

@Configuration
public class CEPMiddlewareConfiguration {
	
	private Map<String, CEPEngine> cepEngines;
	
	public CEPMiddlewareConfiguration() {
		this.cepEngines = new HashMap<String, CEPEngine>();
		this.cepEngines.put("perseo-core", new PerseoCore("http://localhost:8080"));
		this.cepEngines.put("perseo-fe", new PerseoFrontEnd("http://localhost:9090"));
		this.cepEngines.put("error", new ErrorCEPEngine());
	}
	
	public CEPEngine getEngine(String id) {
		return cepEngines.getOrDefault(id, cepEngines.get("error"));
	}
	
	public Object[] getEngines() {
		return cepEngines.values().toArray();
	}
}
