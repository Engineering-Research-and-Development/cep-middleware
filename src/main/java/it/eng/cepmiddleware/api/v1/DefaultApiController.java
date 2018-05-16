package it.eng.cepmiddleware.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-05-15T12:18:46.813Z")

@Controller
public class DefaultApiController implements DefaultApi {

    public ResponseEntity<RootGetResponseBody> rootGet() {
        return new ResponseEntity<RootGetResponseBody>(new RootGetResponseBody(), HttpStatus.ACCEPTED);
    }

}
