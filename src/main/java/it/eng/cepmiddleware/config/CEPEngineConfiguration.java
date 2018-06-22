package it.eng.cepmiddleware.config;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import it.eng.cepmiddleware.engine.CEPEngine;
import it.eng.cepmiddleware.engine.PerseoCore;
import it.eng.cepmiddleware.engine.PerseoFrontEnd;
import it.eng.cepmiddleware.engine.ErrorCEPEngine;

@Configuration
@ConfigurationProperties()
public class CEPEngineConfiguration {

	private Map<String, CEPEngine> cepEngines;
	private Map<String, Class> supportedEngines = new HashMap<>();
	private Class errorEngine = ErrorCEPEngine.class;

	CEPEngineConfiguration() {
		supportedEngines.put("PerseoCore", PerseoCore.class);
		supportedEngines.put("PerseoFrontEnd", PerseoFrontEnd.class);
	}

	public void setEngines(EngineBuildToken[] buildTokens) {
		cepEngines = new HashMap<>();
		for (int i = 0; i < buildTokens.length ; i++) {
			try {
				cepEngines.put(
					buildTokens[i].getEngineId(),
					((CEPEngine)supportedEngines.getOrDefault(
						buildTokens[i].getEngineType(),
						errorEngine
					).getConstructor(
						String.class,
						String.class
					).newInstance(
						buildTokens[i].getEngineId(),
						buildTokens[i].getHostUrl()
					))
				);
			} catch (Exception e) {
				System.out.println("Couldn't create an engine using engines[" + i + "]");
			}
		}
	}

	public Map<String, CEPEngine> getCepEngines() {
		return this.cepEngines;
	}

}
