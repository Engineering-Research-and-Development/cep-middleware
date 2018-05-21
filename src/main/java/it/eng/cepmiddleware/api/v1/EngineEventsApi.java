package it.eng.cepmiddleware.api.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface EngineEventsApi {

    @ApiOperation(value = "Send an event to a CEP engine")
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Event was successfully sent to an engine"),
        @ApiResponse(code = 404, message = "Engine doesn't exist")
    })
    @RequestMapping(value = "/engines/{engineId}/events", method = RequestMethod.POST)
    ResponseEntity<?> enginesEngineIdEventsPost(@ApiParam(value = "",required=true) @PathVariable("engineId") String engineId);
    
}
