package it.eng.cepmiddleware.engine;

import it.eng.cepmiddleware.rule.Rule;
import it.eng.cepmiddleware.engine.perseo_core.PerseoCoreNativeRule;

public class NativeToUniformPerseoCoreRuleAdapter extends Rule {

	NativeToUniformPerseoCoreRuleAdapter(PerseoCoreNativeRule nativeRule) {
		this.setRuleId(nativeRule.getName());
		this.setStatement(nativeRule.getText());
	}

}
