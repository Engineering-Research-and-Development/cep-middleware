package it.eng.cepmiddleware.api.v1.engines;

import io.swagger.annotations.*;
import it.eng.cepmiddleware.engine.EngineInfoToken;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	ResponseEntity<?> deleteEngine(
		@PathVariable("engineId") String engineId,
		@RequestParam(name="cascade", defaultValue="false") Boolean cascade
	);

	@ApiOperation(value = "Add a CEP engine")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful operation")
	})
	@RequestMapping(value = "/engines", method = RequestMethod.POST)
	ResponseEntity<?> addEngine(
		@Valid @RequestBody EngineInfoToken engineInfo
	);

	@ApiOperation(value = "Update properties of a specified engine")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful operation"),
	    @ApiResponse(code = 404, message = "Requested CEP engine does not exist")
	})
	@RequestMapping(value = "/engines/{engineId}", method = RequestMethod.PUT)
	ResponseEntity<?> updateEngine(
		@PathVariable("engineId") String engineId,
		@Valid @RequestBody EngineInfoToken engineInfo
	);

	@ApiOperation(value = "Returns a collection of event types supported by the engine")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful operation"),
	    @ApiResponse(code = 404, message = "Requested CEP engine does not exist")
	})
	@RequestMapping(value = "/engines/{engineId}/event-types", method = RequestMethod.GET)
	ResponseEntity<?> getEngineSupportedEventTypes(
		@PathVariable("engineId") String engineId
	);

	@ApiOperation(value = "Attempts exposing of the given engine")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful operation"),
	    @ApiResponse(code = 404, message = "Requested CEP engine does not exist")
	})
	@RequestMapping(value = "/engines/{engineId}/exposure", method = RequestMethod.POST)
	ResponseEntity<?> setEngineExposure(@PathVariable("engineId") String engineId);

}
