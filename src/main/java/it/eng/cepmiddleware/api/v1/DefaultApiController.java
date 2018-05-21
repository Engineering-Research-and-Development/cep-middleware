package it.eng.cepmiddleware.api.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import it.eng.cepmiddleware.Service;

@Controller
public class DefaultApiController implements DefaultApi {
	
	private Service service;
	
	DefaultApiController() {
		this(new RootGetService());
	}
	
	DefaultApiController(Service service) {
		this.service = service;
	}

    public ResponseEntity<?> rootGet() {
        return service.execute();
    }

}
