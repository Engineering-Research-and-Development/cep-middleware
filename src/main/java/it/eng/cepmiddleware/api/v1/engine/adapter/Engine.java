package it.eng.cepmiddleware.api.v1.engine.adapter;

import it.eng.cepmiddleware.engine.CEPEngine;

public class Engine {

	private CEPEngine engine;

	public Engine(CEPEngine engine) {
		this.engine = engine;
	}

	public String getName() {
		return engine.getName();
	}

	public String getEngineType() {
		return engine.getClass().getSimpleName();
	}

}
