package it.eng.cepmiddleware.api.v1.engine;

import it.eng.cepmiddleware.Service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class EngineApiController implements EngineApi {

	@Autowired private Service getEnginesService;
	@Autowired private Service getEngineService;

	public ResponseEntity<?> getEngine(@PathVariable("engineId") String engineId) {
        return getEngineService.execute(engineId);
    }

    public ResponseEntity<?> getEngines() {
        return getEnginesService.execute();
    }

	public ResponseEntity<?> deleteEngine(@PathVariable("engineId") String engineId) {
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<?> addEngine(
		@Valid @RequestBody Object engineInfo
	) {
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<?> updateEngine(
		@PathVariable("engineId") String engineId,
		@Valid @RequestBody Object engineInfo
	) {
		return ResponseEntity.ok().build();
	}

}
