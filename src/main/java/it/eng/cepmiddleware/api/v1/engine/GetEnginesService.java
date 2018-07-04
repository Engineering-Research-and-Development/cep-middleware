package it.eng.cepmiddleware.api.v1.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.Service;
import it.eng.cepmiddleware.config.CEPMiddlewareConfiguration;

@org.springframework.stereotype.Service
public class GetEnginesService implements Service {

	@Autowired CEPMiddlewareConfiguration config;

	@Override
	public ResponseEntity<?> execute(Object ...parameters) {
		return new ResponseEntity<>(
			config
				.getEngines()
				.stream()
				.<EngineAdapter>map(
					engine -> new EngineAdapter(engine)
				).toArray()
			,
			HttpStatus.OK
		);
	}

}
