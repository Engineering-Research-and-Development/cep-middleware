package it.eng.cepmiddleware.api.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-05-15T12:18:46.813Z")

@Controller
public class EnginesApiController implements EnginesApi {

    private static final Logger log = LoggerFactory.getLogger(EnginesApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public EnginesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> enginesEngineIdEventsPost(@ApiParam(value = "",required=true) @PathVariable("engineId") String engineId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> enginesEngineIdGet(@ApiParam(value = "",required=true) @PathVariable("engineId") String engineId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> enginesEngineIdRulesGet(@ApiParam(value = "",required=true) @PathVariable("engineId") String engineId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> enginesEngineIdRulesPost(@ApiParam(value = "",required=true) @PathVariable("engineId") String engineId,@ApiParam(value = "" ,required=true )  @Valid @RequestBody String rule) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> enginesEngineIdRulesRuleIdDelete(@ApiParam(value = "",required=true) @PathVariable("engineId") String engineId,@ApiParam(value = "",required=true) @PathVariable("ruleId") String ruleId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> enginesEngineIdRulesRuleIdEnabledGet(@ApiParam(value = "",required=true) @PathVariable("engineId") String engineId,@ApiParam(value = "",required=true) @PathVariable("ruleId") String ruleId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> enginesEngineIdRulesRuleIdEnabledPut(@ApiParam(value = "",required=true) @PathVariable("engineId") String engineId,@ApiParam(value = "",required=true) @PathVariable("ruleId") String ruleId,@ApiParam(value = "" ,required=true )  @Valid @RequestBody Boolean enableSwitch) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> enginesEngineIdRulesRuleIdGet(@ApiParam(value = "",required=true) @PathVariable("engineId") String engineId,@ApiParam(value = "",required=true) @PathVariable("ruleId") String ruleId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> enginesEngineIdRulesRuleIdPut(@ApiParam(value = "",required=true) @PathVariable("engineId") String engineId,@ApiParam(value = "",required=true) @PathVariable("ruleId") String ruleId,@ApiParam(value = "" ,required=true )  @Valid @RequestBody String rule) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> enginesGet() {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
