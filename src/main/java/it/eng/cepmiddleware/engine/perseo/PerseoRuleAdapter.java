package it.eng.cepmiddleware.engine.perseo;

import java.util.Map;

import it.eng.cepmiddleware.ObjectMapperProvider;
import it.eng.cepmiddleware.rule.PerseoRule;
import it.eng.cepmiddleware.rule.Rule;

public class PerseoRuleAdapter {

	private String name;
	private String text;
	private String action;

	public PerseoRuleAdapter(PerseoRule rule) {
		this.name = rule.getName();
		this.text = rule.getText();
		this.action = rule.getAction();
	}

	public String getName() {
		return name;
	}

	public String getText() {
		return text;
	}
	
	public Map getAction() {
		return ObjectMapperProvider
				.getObjectMapper()
				.readValue(action, Map.class);
	}

	@Override
	public String toString() {
		return "PerseoFERuleAdapter [name=" + name + ", text=" + text + ", action=" + action + "]";
	}

}
