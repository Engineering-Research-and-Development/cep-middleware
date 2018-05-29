package it.eng.cepmiddleware.engine;

import it.eng.cepmiddleware.rule.Rule;

public class PerseoCoreRuleAdapter {

	private String name;
	private String text;

	public PerseoCoreRuleAdapter(Rule rule) {
		this.name = rule.getRuleId();
		this.text = rule.getStatement();
	}

	public String getName() {
		return name;
	}

	public String getText() {
		return text;
	}

}
