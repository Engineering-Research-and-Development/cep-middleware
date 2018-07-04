package it.eng.cepmiddleware;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@FunctionalInterface
public interface Service {

	ResponseEntity<String> paramError = new ResponseEntity<String>(
		"Correct parameters not provided",
		HttpStatus.BAD_REQUEST
	);

	public ResponseEntity<?> execute(Object ...parameters);

}
