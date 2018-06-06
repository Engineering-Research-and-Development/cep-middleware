package it.eng.cepmiddleware.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import it.eng.cepmiddleware.engine.CEPEngine;
import it.eng.cepmiddleware.engine.ErrorCEPEngine;
import it.eng.cepmiddleware.engine.PerseoCore;
import it.eng.cepmiddleware.engine.PerseoFrontEnd;

@Configuration
public class CEPMiddlewareConfiguration {

	@Value("${path.perseo-core}") String perseoCorePath;
	@Value("${path.perseo-fe}") String perseoFEPath;
	private Map<String, CEPEngine> cepEngines;
	private CEPEngine error = new ErrorCEPEngine();

	@PostConstruct
	private void init() {
		this.cepEngines = new HashMap<String, CEPEngine>();
		this.cepEngines.put("perseo-core", new PerseoCore("perseo-core", perseoCorePath));
		this.cepEngines.put("perseo-fe", new PerseoFrontEnd("perseo-fe", perseoFEPath));
	}

	public CEPEngine getEngine(String id) {
		return cepEngines.getOrDefault(id, error);
	}

	public Collection<CEPEngine> getEngines() {
		return cepEngines.values();
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("PATCH");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("DELETE");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

}
