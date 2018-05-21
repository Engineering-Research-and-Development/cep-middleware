package it.eng.cepmiddleware.api.v1;

import io.swagger.annotations.*;
import it.eng.cepmiddleware.CEPRule;
import it.eng.cepmiddleware.PerseoCore;
import it.eng.cepmiddleware.PerseoFrontEnd;
import it.eng.cepmiddleware.config.CEPMiddlewareConfiguration;
import it.eng.cepmiddleware.CEPEngine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-05-15T12:18:46.813Z")

@Controller
public class EngineApiController implements EngineApi {
	
	@Autowired
	CEPMiddlewareConfiguration config;

    public ResponseEntity<Void> getEngine(@ApiParam(value = "",required=true) @PathVariable("engineId") String engineId) {
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<?> getEngines() {
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
