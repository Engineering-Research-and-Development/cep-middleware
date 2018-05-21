package it.eng.cepmiddleware.api.v1.engine;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.Service;
import it.eng.cepmiddleware.config.CEPMiddlewareConfiguration;

public class GetEnginesService implements Service {

	private ResponseEntity<Object[]> response;

	public GetEnginesService() {
		this.response = new ResponseEntity<Object[]>(
			(new CEPMiddlewareConfiguration()).getEngines(),
			HttpStatus.OK
		);
	}

	@Override
	public ResponseEntity<?> execute(Object ...parameters) {
		return response;
	}

}
