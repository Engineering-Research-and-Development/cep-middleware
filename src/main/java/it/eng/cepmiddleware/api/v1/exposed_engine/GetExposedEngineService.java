package it.eng.cepmiddleware.api.v1.exposed_engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.Service;
import it.eng.cepmiddleware.engine.EngineExposer;

@org.springframework.stereotype.Service
public class GetExposedEngineService implements Service {

	@Autowired private EngineExposer engineExposer;

	@Override
	public ResponseEntity<?> execute(Object... parameters) {
		return new ResponseEntity<>(
			engineExposer.getExposedEngineInfo(),
			HttpStatus.OK
		);
	}

}
