package it.eng.cepmiddleware.engine;

import it.eng.cepmiddleware.rule.PerseoFERule;

public class NativeToUniformPerseoFrontEndRule extends PerseoFERule {

	public NativeToUniformPerseoFrontEndRule(PerseoFrontEndNativeRule response) {
		this.setRuleId(response.getName());
		this.setStatement(response.getText());
		this.setAction(response.getAction());
	}

}
