package it.eng.cepmiddleware.api.v1.engines;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import it.eng.cepmiddleware.engine.EngineInfoToken;

@Controller
public class EngineApiController implements EngineApi {

	@Autowired private GetEnginesService getEnginesService;
	@Autowired private GetEngineService getEngineService;
	@Autowired private DeleteEngineService deleteEngineService;
	@Autowired private AddEngineService addEngineService;
	@Autowired private UpdateEngineService updateEngineService;
	@Autowired private GetEngineSupportedEventTypesService getEngineSupportedEventTypesService;

	public ResponseEntity<?> getEngine(@PathVariable("engineId") String engineId) {
        return getEngineService.execute(engineId);
    }

    public ResponseEntity<?> getEngines() {
        return getEnginesService.execute();
    }

	public ResponseEntity<?> deleteEngine(
		@PathVariable("engineId") String engineId,
		@RequestParam(name="cascade", defaultValue="false") Boolean cascade
	) {
		return deleteEngineService.execute(engineId, cascade);
	}

	@Override
	public ResponseEntity<?> addEngine(
		@Valid @RequestBody EngineInfoToken engineInfo
	) {
		return addEngineService.execute(engineInfo);
	}

	@Override
	public ResponseEntity<?> updateEngine(
		@PathVariable("engineId") String engineId,
		@Valid @RequestBody EngineInfoToken engineInfo
	) {
		return updateEngineService.execute(engineId, engineInfo);
	}

	@Override
	public ResponseEntity<?> getEngineSupportedEventTypes(
		@PathVariable("engineId") String engineId
	) {
		return getEngineSupportedEventTypesService.execute(engineId);
	}

}
