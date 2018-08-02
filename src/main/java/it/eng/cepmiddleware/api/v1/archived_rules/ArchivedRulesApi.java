package it.eng.cepmiddleware.api.v1.archived_rules;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface ArchivedRulesApi {

	@ApiOperation(value = "Returns archived rules")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful operation")
	})
	@RequestMapping(value = "/archived-rules", method = RequestMethod.GET)
	ResponseEntity<?> getArchivedRules(
		@RequestParam(required=false) String type
	);

	@ApiOperation(value = "Returns archived rule")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful operation"),
	    @ApiResponse(code = 404, message = "Archived rule doesn't exist")
	})
	@RequestMapping(value = "/archived-rules/{ruleId}", method = RequestMethod.GET)
	ResponseEntity<?> getArchivedRule(@PathVariable("ruleId") String ruleId);

	@ApiOperation(value = "Reemploys archived rule")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Successful operation"),
	    @ApiResponse(code = 400, message = "Bad request")
	})
	@RequestMapping(value = "/archived-rules/{ruleId}/reassignment", method = RequestMethod.POST)
	ResponseEntity<?> reassignArchivedRule(
		@PathVariable("ruleId") String ruleId,
		@Valid @RequestBody ArchivedRuleReassignment reassignment
	);

}
