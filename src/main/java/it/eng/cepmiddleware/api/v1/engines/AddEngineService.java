package it.eng.cepmiddleware.api.v1.engines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.Service;
import it.eng.cepmiddleware.engine.EngineInfoToken;
import it.eng.cepmiddleware.engine.EngineInfoTokenRepository;
import it.eng.cepmiddleware.responses.PlainResponseBody;

@org.springframework.stereotype.Service
public class AddEngineService implements Service {

	@Autowired MapToEngineConverter converter;
	@Autowired private EngineInfoTokenRepository repository;

	@Override
	public ResponseEntity<?> execute(Object... parameters) {
		if (parameters[0] instanceof EngineInfoToken) {
			EngineInfoToken engineInfo = (EngineInfoToken) parameters[0];
			return addEngine(engineInfo);
		}
		return Service.paramError;
	}

	private ResponseEntity<?> addEngine(EngineInfoToken engineInfo) {
		try {
			if (repository.findById(engineInfo.getEngineId()).isPresent()) {
				return new ResponseEntity<>(
					new PlainResponseBody(String.format(
						"%s engine already exists.",
						engineInfo.getEngineId()
					)),
					HttpStatus.CONFLICT
				);
			}
			repository.save(engineInfo);
			return new ResponseEntity<>(
				new PlainResponseBody(String.format(
					"%s engine created",
					engineInfo.getEngineId()
				)),
				HttpStatus.OK
			);
		} catch (Exception e) {
			return new ResponseEntity<>(
				new PlainResponseBody(e.getMessage()),
				HttpStatus.BAD_REQUEST
			);
		}
	}

}
