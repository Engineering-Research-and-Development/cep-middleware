package it.eng.cepmiddleware.engine.perseo_front_end;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"_id"})
public class PerseoFrontEndNativeRule {

	private String name;
	private String text;
	private Map action;
	private Object subservice;
	private Object service;

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

	public Map getAction() {
		return action;
	}

	public void setAction(Map action) {
		this.action = action;
	}

	public Object getSubservice() {
		return subservice;
	}

	public void setSubservice(Object subservice) {
		this.subservice = subservice;
	}

	public Object getService() {
		return service;
	}

	public void setService(Object service) {
		this.service = service;
	}

	@Override
	public String toString() {
		return "PerseoFrontEndNativeRule [name=" + name + ", text=" + text + ", action=" + action + "]";
	}

}
