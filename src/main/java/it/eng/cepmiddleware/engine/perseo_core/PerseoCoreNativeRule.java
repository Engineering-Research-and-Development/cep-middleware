package it.eng.cepmiddleware.engine.perseo_core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"timeLastStateChange", "state"})
public class PerseoCoreNativeRule {

	private String name;
	private String text;

	public PerseoCoreNativeRule() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "PerseoCoreNativeRule [name = " + name + " , text = " + text + "]";
	}

}
