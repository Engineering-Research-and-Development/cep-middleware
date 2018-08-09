package it.eng.cepmiddleware.api.v1.exposed_engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.Service;
import it.eng.cepmiddleware.engine.EngineExposer;
import it.eng.cepmiddleware.engine.EngineInfoToken;
import it.eng.cepmiddleware.responses.PlainResponseBody;

@org.springframework.stereotype.Service
public class GetExposedEngineService implements Service {

	@Autowired private EngineExposer engineExposer;

	@Override
	public ResponseEntity<?> execute(Object... parameters) {
		EngineInfoToken engineInfo = engineExposer.getExposedEngineInfo();
		if (engineInfo != null) {
			return new ResponseEntity<>(
				engineExposer.getExposedEngineInfo(),
				HttpStatus.OK
			);
		}
		return new ResponseEntity<>(
			new PlainResponseBody(
				"No engine has been exposed"
			),
			HttpStatus.NOT_FOUND
		);
	}

}
