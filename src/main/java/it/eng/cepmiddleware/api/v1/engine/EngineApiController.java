package it.eng.cepmiddleware.api.v1.engine;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class EngineApiController implements EngineApi {

	@Autowired private GetEnginesService getEnginesService;
	@Autowired private GetEngineService getEngineService;
	@Autowired private DeleteEngineService deleteEngineService;
	@Autowired private AddEngineService addEngineService;
	@Autowired private UpdateEngineService updateEngineService;

	public ResponseEntity<?> getEngine(@PathVariable("engineId") String engineId) {
        return getEngineService.execute(engineId);
    }

    public ResponseEntity<?> getEngines() {
        return getEnginesService.execute();
    }

	public ResponseEntity<?> deleteEngine(@PathVariable("engineId") String engineId) {
		return deleteEngineService.execute(engineId);
	}

	@Override
	public ResponseEntity<?> addEngine(
		@Valid @RequestBody Map<String, String> engineInfo
	) {
		return addEngineService.execute(engineInfo);
	}

	@Override
	public ResponseEntity<?> updateEngine(
		@Valid @RequestBody Map<String, String> engineInfo
	) {
		return updateEngineService.execute(engineInfo);
	}

}
