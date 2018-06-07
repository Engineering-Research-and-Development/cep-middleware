package it.eng.cepmiddleware.api.v1.engine.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.Service;
import it.eng.cepmiddleware.engine.CEPEngineFactory;

@org.springframework.stereotype.Service
public class PostEventService implements Service {

	@Autowired CEPEngineFactory engineFactory;

	@Override
	public ResponseEntity<?> execute(Object... parameters) {
		if (parameters[0] instanceof String && parameters[1] instanceof Object) {
			String engineId = (String) parameters[0];
			Object event = parameters[1];
			return postEvent(engineId, event);
		}
		return ResponseEntity.badRequest().build();
	}

	private ResponseEntity<?> postEvent(String engineId, Object event) {
		return engineFactory.getCEPEngine(engineId).postEvent(event);
	}

}
