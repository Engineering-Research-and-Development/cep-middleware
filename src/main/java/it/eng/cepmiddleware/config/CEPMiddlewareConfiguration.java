package it.eng.cepmiddleware.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import it.eng.cepmiddleware.engine.CEPEngine;
import it.eng.cepmiddleware.engine.ErrorCEPEngine;
import it.eng.cepmiddleware.engine.PerseoCore;
import it.eng.cepmiddleware.engine.PerseoFrontEnd;

@Configuration
public class CEPMiddlewareConfiguration {
	
	private Map<String, CEPEngine> cepEngines;

	@Value("${path.perseo-core}")
	String perseoCorePath;

	@Value("${path.perseo-fe}")
	String perseoFEPath;
	
	@PostConstruct
	private void init() {
		this.cepEngines = new HashMap<String, CEPEngine>();
		this.cepEngines.put("perseo-core", new PerseoCore("perseo-core", perseoCorePath));
		this.cepEngines.put("perseo-fe", new PerseoFrontEnd("perseo-fe", perseoFEPath));
		this.cepEngines.put("error", new ErrorCEPEngine());
	}
	
	public CEPEngine getEngine(String id) {
		return cepEngines.getOrDefault(id, cepEngines.get("error"));
	}
	
	public Collection<CEPEngine> getEngines() {
		return cepEngines.values();
	}
}
