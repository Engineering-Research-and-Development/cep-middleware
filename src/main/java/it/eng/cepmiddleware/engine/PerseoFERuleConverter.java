package it.eng.cepmiddleware.engine;

import java.util.Map;

import it.eng.cepmiddleware.Converter;
import it.eng.cepmiddleware.rule.PerseoFERule;

public class PerseoFERuleConverter implements Converter<PerseoFERule, Map<String, Object>> {

	private String ownerEngineName;

	PerseoFERuleConverter(String ownerEngineName) {
		this.ownerEngineName = ownerEngineName;
	}

	@Override
	public PerseoFERule convert(Map<String, Object> source) {
		PerseoFERule rule = new PerseoFERule();
		rule.setOwner(ownerEngineName);
		rule.setDescription((String)source.get("description"));
		rule.setStatement((String)source.get("statement"));
		rule.setAction((Map)source.get("action"));
		return rule;
	}

}