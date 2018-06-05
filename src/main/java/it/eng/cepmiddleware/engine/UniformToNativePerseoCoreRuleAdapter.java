package it.eng.cepmiddleware.engine;

import it.eng.cepmiddleware.engine.perseo_core.PerseoCoreNativeRule;
import it.eng.cepmiddleware.rule.Rule;

public class UniformToNativePerseoCoreRuleAdapter extends PerseoCoreNativeRule {

	UniformToNativePerseoCoreRuleAdapter(Rule rule) {
		this.setName(rule.getRuleId());
		this.setText(rule.getStatement());
	}

}
