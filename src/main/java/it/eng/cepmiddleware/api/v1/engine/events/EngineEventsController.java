package it.eng.cepmiddleware.api.v1.engine.events;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class EngineEventsController implements EngineEventsApi {

	@Autowired PostEventService postEventService;

	@Override
	public ResponseEntity<?> postEvent(
		@PathVariable("engineId") String engineId,
		@RequestBody @Valid Object event
	) {
		return postEventService.execute(engineId, event);
	}

	@ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
	public ResponseEntity<?> test() {
		return new ResponseEntity<>("Your JSON input is faulty", HttpStatus.BAD_REQUEST);
	}

}
