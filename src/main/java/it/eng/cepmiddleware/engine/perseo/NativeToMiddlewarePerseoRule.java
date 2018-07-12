package it.eng.cepmiddleware.engine.perseo;

import it.eng.cepmiddleware.rule.PerseoRule;

public class NativeToMiddlewarePerseoRule extends PerseoRule {

	public NativeToMiddlewarePerseoRule(PerseoNativeRule response) {
		this.setRuleId(response.getName());
		this.setText(response.getText());
		this.setAction(response.getAction());
	}

}
