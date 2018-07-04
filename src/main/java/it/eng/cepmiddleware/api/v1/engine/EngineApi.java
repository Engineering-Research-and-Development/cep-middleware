package it.eng.cepmiddleware.api.v1.engine;

import io.swagger.annotations.*;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface EngineApi {

	@ApiOperation(value = "Returns info about the engine")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful operation"),
	    @ApiResponse(code = 404, message = "Requested CEP engine does not exist")
	})
	@RequestMapping(value = "/engines/{engineId}", method = RequestMethod.GET)
	ResponseEntity<?> getEngine(@PathVariable("engineId") String engineId);

	@ApiOperation(value = "Returns all available CEP engines")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful operation")
	})
	@RequestMapping(value = "/engines", method = RequestMethod.GET)
	ResponseEntity<?> getEngines();

	@ApiOperation(value = "Remove a CEP engine")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful operation"),
	    @ApiResponse(code = 404, message = "Requested CEP engine does not exist")
	})
	@RequestMapping(value = "/engines/{engineId}", method = RequestMethod.DELETE)
	ResponseEntity<?> deleteEngine(@PathVariable("engineId") String engineId);

	@ApiOperation(value = "Add a CEP engine")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful operation")
	})
	@RequestMapping(value = "/engines", method = RequestMethod.POST)
	ResponseEntity<?> addEngine(
		@Valid @RequestBody Map<String, String> engineInfo
	);

	@ApiOperation(value = "Update properties of a specified engine")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful operation"),
	    @ApiResponse(code = 404, message = "Requested CEP engine does not exist")
	})
	@RequestMapping(value = "/engines", method = RequestMethod.PUT)
	ResponseEntity<?> updateEngine(
		@Valid @RequestBody Map<String, String> engineInfo
	);
}
