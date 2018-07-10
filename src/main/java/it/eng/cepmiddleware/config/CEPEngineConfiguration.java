package it.eng.cepmiddleware.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import it.eng.cepmiddleware.engine.CEPEngine;
import it.eng.cepmiddleware.engine.perseo_front_end.PerseoFrontEnd;
import it.eng.cepmiddleware.engine.ErrorCEPEngine;

@Configuration
@ConfigurationProperties()
public class CEPEngineConfiguration {

	private Map<String, CEPEngine> cepEngines;
	private Map<String, Class> supportedEngines = new HashMap<>();
	private Class errorEngine = ErrorCEPEngine.class;

	CEPEngineConfiguration() {
		supportedEngines.put("PerseoFrontEnd", PerseoFrontEnd.class);
	}
	
	@Bean()
	@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	CEPEngine getEngine(EngineBuildToken buildToken) throws Exception {
		return ((CEPEngine)supportedEngines.getOrDefault(
			buildToken.getEngineType(),
			errorEngine
		).getConstructor(
			String.class,
			String.class
		).newInstance(
			buildToken.getEngineId(),
			buildToken.getHostUrl()
		));
	}

	public void setEngines(EngineBuildToken[] buildTokens) {
		cepEngines = new HashMap<>();
		for (int i = 0; i < buildTokens.length ; i++) {
			try {
				cepEngines.put(
					buildTokens[i].getEngineId(),
					getEngine(buildTokens[i])
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
