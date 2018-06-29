package it.eng.cepmiddleware.engine.perseo_core;

import it.eng.cepmiddleware.rule.Rule;

public class UniformToNativePerseoCoreRuleAdapter extends PerseoCoreNativeRule {

	UniformToNativePerseoCoreRuleAdapter(Rule rule) {
		this.setName(rule.getRuleId());
		this.setText(rule.getStatement());
	}

}
