package it.eng.cepmiddleware.api.v1.engine;

import java.util.Map;

import org.springframework.stereotype.Component;

import it.eng.cepmiddleware.Converter;
import it.eng.cepmiddleware.engine.CEPEngine;
import it.eng.cepmiddleware.engine.ErrorCEPEngine;
import it.eng.cepmiddleware.engine.perseo.Perseo;

@Component
public class MapToEngineConverter implements Converter<CEPEngine, Map<String, String>> {

	@Override
	public CEPEngine convert(Map<String, String> source) {
		String engineType = source.get("engineType");
		String engineId = source.get("engineId");
		String hostUrl = source.get("hostUrl");
		if (
			engineType == null ||
			engineId == null ||
			hostUrl == null
		) {
			return new ErrorCEPEngine();
		}
		switch (engineType) {
			case "PerseoFrontEnd": return new Perseo(engineId, hostUrl);
			default: return new ErrorCEPEngine();
		}
	}

}
