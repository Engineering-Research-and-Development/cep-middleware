package it.eng.cepmiddleware.api.v1.engine;

import io.swagger.annotations.*;
import it.eng.cepmiddleware.Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EngineApiController implements EngineApi {
	
	private Service getEnginesService;
	private Service getEngineService;
	
	public EngineApiController() {
		this(new GetEnginesService(), new GetEngineService());
	}
	
	public EngineApiController(
		Service getEnginesService,
		Service getEngineService
	) {
		this.getEnginesService = getEnginesService;
		this.getEngineService = getEngineService;
	}

    public ResponseEntity<?> getEngine(@ApiParam(value = "",required=true) @PathVariable("engineId") String engineId) {
        return getEngineService.execute(engineId);
    }

    public ResponseEntity<?> getEngines() {
        return getEnginesService.execute();
    }

}
