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
import it.eng.cepmiddleware.engine.perseo.Perseo;

@Configuration
@ConfigurationProperties()
public class CEPEngineConfiguration {

	private Map<String, CEPEngine> cepEngines = new HashMap<>();
	private Map<String, ImmutableEngineInfoToken> cepEnginesInfo = new HashMap<>();
	private Map<String, Class> supportedEngines = new HashMap<>();

	CEPEngineConfiguration() {
		supportedEngines.put("Perseo", Perseo.class);
	}

	@Bean()
	@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	CEPEngine engine(EngineInfoToken buildToken) throws Exception {
		String engineId = buildToken.getEngineId();
		String hostUrl = buildToken.getHostUrl();
		if (engineId == null || hostUrl == null) {
			throw new Exception("engineId or hostUrl were not provided");
		}
		return ((CEPEngine)supportedEngines.get(
			buildToken.getEngineType()
		).getConstructor(
			String.class,
			String.class
		).newInstance(
			engineId,
			hostUrl
		));
	}

	public void setEngines(EngineInfoToken[] tokens) {
		for (int i = 0 ; i < tokens.length ; i++) {
			try {
				CEPEngine engine = engine(tokens[i]);
				String engineId = tokens[i].getEngineId();
				cepEngines.put(engineId, engine);
				cepEnginesInfo.put(engineId, new ImmutableEngineInfoToken(tokens[i]));
			} catch (Exception e) {
				System.out.println("Couldn't create an engine using engines[" + i + "]");
			}
		}
	}

	public Collection<ImmutableEngineInfoToken> getEnginesInfo() {
		return cepEnginesInfo.values();
	}

	public Optional<ImmutableEngineInfoToken> getEngineInfo(String engineId) {
		return Optional.ofNullable(cepEnginesInfo.get(engineId));
	}

	public Collection<CEPEngine> getCepEngines() {
		return this.cepEngines.values();
	}

	public Optional<CEPEngine> getCepEngine(String engineId) {
		return Optional.ofNullable(this.cepEngines.get(engineId));
	}

	public CEPEngine putCepEngine(ImmutableEngineInfoToken token) throws Exception {
		CEPEngine engine = this.engine(new EngineInfoToken(token));
		String engineId = token.getEngineId();
		this.cepEngines.put(engineId, engine);
		this.cepEnginesInfo.put(engineId, token);
		return engine;
	}

	public CEPEngine remove(String engineId) {
		CEPEngine deletedEngine = this.cepEngines.remove(engineId);
		this.cepEnginesInfo.remove(engineId);
		return deletedEngine;
	}
}
