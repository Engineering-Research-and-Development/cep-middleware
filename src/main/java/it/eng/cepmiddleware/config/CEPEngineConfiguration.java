package it.eng.cepmiddleware.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import it.eng.cepmiddleware.engine.CEPEngine;
import it.eng.cepmiddleware.engine.ErrorCEPEngine;
import it.eng.cepmiddleware.engine.perseo.Perseo;

@Configuration
@ConfigurationProperties()
public class CEPEngineConfiguration {

	private Map<EngineInfoToken, CEPEngine> cepEngines = new HashMap<>();
	private Map<String, Class> supportedEngines = new HashMap<>();

	CEPEngineConfiguration() {
		supportedEngines.put("Perseo", Perseo.class);
	}
	
	@Bean()
	@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	CEPEngine engine(EngineInfoToken buildToken) throws Exception {
		return ((CEPEngine)supportedEngines.get(
			buildToken.getEngineType()
		).getConstructor(
			String.class,
			String.class
		).newInstance(
			buildToken.getEngineId(),
			buildToken.getHostUrl()
		));
	}

	public void setEngines(EngineInfoToken[] tokens) {
		for (int i = 0 ; i < tokens.length ; i++) {
			try {
				CEPEngine engine = engine(tokens[i]);
				cepEngines.put(
					tokens[i],
					engine
				);
			} catch (Exception e) {
				System.out.println("Couldn't create an engine using engines[" + i + "]");
			}
		}
	}

	public Collection<EngineInfoToken> getEnginesInfo() {
		return cepEngines.keySet();
	}

	public Optional<EngineInfoToken> getEngineInfo(String engineId) {
		return cepEngines.keySet().stream().filter((elem) -> {
			return elem.getEngineId().equals(engineId);
		}).findFirst();
	}

	public Map<EngineInfoToken, CEPEngine> getCepEngines() {
		return this.cepEngines;
	}
	
	public Optional<CEPEngine> getCepEngine(String engineId) {
		return Optional.ofNullable(this.cepEngines.get(
			getEngineInfo(engineId).orElse(null)
		));
	}
	
	public CEPEngine putCepEngine(EngineInfoToken token) throws Exception {
		return cepEngines.put(token, engine(token));
	}

}
