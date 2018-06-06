package it.eng.cepmiddleware.engine;

import java.util.Map;

import it.eng.cepmiddleware.ObjectMapperProvider;
import it.eng.cepmiddleware.rule.PerseoFERule;
import it.eng.cepmiddleware.rule.Rule;

public class PerseoFERuleAdapter {

	private String name;
	private String text;
	private String action;

	public PerseoFERuleAdapter(Rule rule) {
		this.name = rule.getRuleId();
		this.text = rule.getStatement();
		try {
			this.action = ((PerseoFERule) rule).getAction();
		} catch (Exception e) {
			this.action = null;
		}
	}

	public PerseoFERuleAdapter(PerseoFERule rule) {
		this.name = rule.getRuleId();
		this.text = rule.getStatement();
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
