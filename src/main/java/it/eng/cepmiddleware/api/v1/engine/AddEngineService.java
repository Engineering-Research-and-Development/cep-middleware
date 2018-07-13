package it.eng.cepmiddleware.api.v1.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.Service;
import it.eng.cepmiddleware.config.CEPEngineConfiguration;
import it.eng.cepmiddleware.config.EngineInfoToken;
import it.eng.cepmiddleware.responses.PlainResponseBody;

@org.springframework.stereotype.Service
public class AddEngineService implements Service {
	
	@Autowired MapToEngineConverter converter;
	@Autowired CEPEngineConfiguration engineConfig;

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
			if (engineConfig.getCepEngine(engineInfo.getEngineId()).isPresent()) {
				return new ResponseEntity<>(
					new PlainResponseBody(String.format(
						"%s engine already exists.",
						engineInfo.getEngineId()
					)),
					HttpStatus.CONFLICT
				);
			}
			engineConfig.putCepEngine(engineInfo.getImmutable());
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