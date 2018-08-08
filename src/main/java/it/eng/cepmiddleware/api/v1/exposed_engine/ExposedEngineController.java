package it.eng.cepmiddleware.api.v1.exposed_engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class ExposedEngineController implements ExposedEngineApi {

	@Autowired private GetExposedEngineService getExposedEngineService;

	@Override
	public ResponseEntity<?> getExposedEngine() {
		return getExposedEngineService.execute();
	}

}
