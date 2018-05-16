package it.eng.cepmiddleware.api.v1;

public class RootGetResponseBody {
	
	private String allEngines = "/engines";
	private String singleEngine = "/engines/{engineId}";
	
	public String getAllEngines() {
		return allEngines;
	}
	public String getSingleEngine() {
		return singleEngine;
	}
	
}
