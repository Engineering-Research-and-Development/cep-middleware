package it.eng.cepmiddleware;

import org.springframework.http.ResponseEntity;

@FunctionalInterface
public interface Service {
	public ResponseEntity<?> execute(Object ...parameters);
}
