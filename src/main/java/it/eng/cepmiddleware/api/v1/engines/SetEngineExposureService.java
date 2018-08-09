package it.eng.cepmiddleware.api.v1.engines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.Service;
import it.eng.cepmiddleware.engine.EngineExposer;
import it.eng.cepmiddleware.engine.EngineInfoTokenRepository;
import it.eng.cepmiddleware.responses.PlainResponseBody;

@org.springframework.stereotype.Service
public class SetEngineExposureService implements Service {

	@Autowired private EngineExposer engineExposer;
	@Autowired private EngineInfoTokenRepository repository;
	private ResponseEntity engineNotFound = new ResponseEntity<>(
		new PlainResponseBody("Engine doesn't exist"),
		HttpStatus.NOT_FOUND
	);

	@Override
	public ResponseEntity<?> execute(Object... parameters) {
		if (parameters[0] instanceof String) {
			String engineId = (String) parameters[0];
			return setEngineExposure(engineId);
		}
		return Service.paramError;
	}

	private ResponseEntity<?> setEngineExposure(String engineId) {
		if (!repository.findById(engineId).isPresent()) {
			return engineNotFound;
		}
		boolean engineExposureSuccessful = engineExposer.setEngineToExpose(engineId);
		if (engineExposureSuccessful) {
			return ResponseEntity.ok(
				new PlainResponseBody("Engine Exposure Successful")
			);
		}
		return new ResponseEntity<>(
			new PlainResponseBody("Engine Exposure Failed"),
			HttpStatus.INTERNAL_SERVER_ERROR
		);
	}

}
