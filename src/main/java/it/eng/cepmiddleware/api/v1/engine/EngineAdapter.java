package it.eng.cepmiddleware.api.v1.engine;

import it.eng.cepmiddleware.engine.CEPEngine;

public class EngineAdapter {

	private CEPEngine engine;

	public EngineAdapter(CEPEngine engine) {
		this.engine = engine;
	}

	public String getEngineId() {
		return engine.getName();
	}

	public String getEngineType() {
		return engine.getClass().getSimpleName();
	}

}
