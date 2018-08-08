package it.eng.cepmiddleware.api.v1.engines.rules;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface EngineRulesApi {

	@ApiOperation(value = "Returns a single rule within the engine")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successful operation"),
		@ApiResponse(code = 404, message = "Requested rule doesn't exist or the engine doesn't exist")
	})
	@RequestMapping(value = "/engines/{engineId}/rules/{ruleId}", method = RequestMethod.GET)
	ResponseEntity<?> getRule(
		@ApiParam(value = "",required=true) @PathVariable("engineId") String engineId,
		@ApiParam(value = "",required=true) @PathVariable("ruleId") String ruleId
	);


	@ApiOperation(value = "Returns all rules within the engine")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successful operation"),
		@ApiResponse(code = 404, message = "Rule doesn't exist or the engine doesn't exist")
	})
	@RequestMapping(value = "/engines/{engineId}/rules", method = RequestMethod.GET)
	ResponseEntity<?> getRules(@ApiParam(value = "",required=true) @PathVariable("engineId") String engineId);


	@ApiOperation(value = "Create a single rule within the engine")
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "Rule successfully created"),
		@ApiResponse(code = 400, message = "Rule doesn't exist or the engine doesn't exist"),
		@ApiResponse(code = 405, message = "A rule with the given Id already exists for the given engine, thus the creation failed")
	})
	@RequestMapping(value = "/engines/{engineId}/rules", method = RequestMethod.POST)
	ResponseEntity<?> createRule(
		@ApiParam(value = "",required=true) @PathVariable("engineId") String engineId,
		@ApiParam(value = "",required=true ) @Valid @RequestBody Map<String, Object> rule
	);


	@ApiOperation(value = "Removes a single rule from the engine")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successful operation"),
		@ApiResponse(code = 404, message = "Requested rule doesn't exist within the given engine or the engine doesn't exist")
	})
	@RequestMapping(value = "/engines/{engineId}/rules/{ruleId}", method = RequestMethod.DELETE)
	ResponseEntity<?> deleteRule(
		@ApiParam(value = "",required=true) @PathVariable("engineId") String engineId,
		@ApiParam(value = "",required=true) @PathVariable("ruleId") String ruleId
	);


	@ApiOperation(value = "Modify a single rule within the engine")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Rule successfully modified"),
		@ApiResponse(code = 404, message = "Requested rule doesn't exist within the given engine or the engine doesn't exist")
	})
	@RequestMapping(value = "/engines/{engineId}/rules/{ruleId}", method = RequestMethod.PUT)
	ResponseEntity<?> updateRule(
		@ApiParam(value = "",required=true) @PathVariable("engineId") String engineId,
		@ApiParam(value = "",required=true) @PathVariable("ruleId") String ruleId,
		@ApiParam(value = "",required=true) @Valid @RequestBody Map<String, Object> rule
	);

}
