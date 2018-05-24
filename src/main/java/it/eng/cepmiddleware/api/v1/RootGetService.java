package it.eng.cepmiddleware.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.Service;

@org.springframework.stereotype.Service
public class RootGetService implements Service {

	ResponseEntity<RootGetResponseBody> responseEntity;
	
	RootGetService() {
		this.responseEntity = new ResponseEntity<RootGetResponseBody>(
			new RootGetResponseBody(),
			HttpStatus.ACCEPTED
		);
	}
	
	@Override
	public  ResponseEntity<?> execute(Object ...parameters) {
		return responseEntity;
	}

}
