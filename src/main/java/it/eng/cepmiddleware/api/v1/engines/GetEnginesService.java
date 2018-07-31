package it.eng.cepmiddleware.api.v1.engines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.Service;
import it.eng.cepmiddleware.engine.EngineInfoTokenRepository;

@org.springframework.stereotype.Service
public class GetEnginesService implements Service {

	@Autowired private EngineInfoTokenRepository repository;

	@Override
	public ResponseEntity<?> execute(Object ...parameters) {
		return new ResponseEntity<>(
			repository.findAll(),
			HttpStatus.OK
		);
	}

}
