package it.eng.cepmiddleware.api.v1.exposed_engine;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface ExposedEngineApi {

	@ApiOperation(value = "Returns exposed engine info")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful operation")
	})
	@RequestMapping(value = "/exposed-engine", method = RequestMethod.GET)
	ResponseEntity<?> getExposedEngine();

}
