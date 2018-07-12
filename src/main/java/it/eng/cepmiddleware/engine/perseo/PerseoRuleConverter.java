package it.eng.cepmiddleware.engine.perseo;

import java.util.Map;

import it.eng.cepmiddleware.Converter;
import it.eng.cepmiddleware.rule.PerseoRule;

public class PerseoRuleConverter implements Converter<PerseoRule, Map<String, Object>> {

	private String ownerEngineName;

	PerseoRuleConverter(String ownerEngineName) {
		this.ownerEngineName = ownerEngineName;
	}

	@Override
	public PerseoRule convert(Map<String, Object> source) {
		PerseoRule rule = new PerseoRule();
		rule.setOwner(ownerEngineName);
		rule.setText((String)source.get("text"));
		rule.setDescription((String)source.get("description"));
		rule.setAction((Map)source.get("action"));
		rule.setName((String)source.get("name"));
		rule.setActive(Boolean.parseBoolean(source.getOrDefault("active", "false").toString()));
		return rule;
	}

}