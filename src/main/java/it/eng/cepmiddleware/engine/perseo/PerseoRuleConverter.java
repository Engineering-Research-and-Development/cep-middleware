package it.eng.cepmiddleware.engine.perseo;

import java.util.Map;

import com.fasterxml.jackson.databind.util.RawValue;

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
		try {
			rule.setAction((Map)source.get("action"));
		} catch (Exception e) {
			rule.setAction((String)((RawValue)source.get("action")).rawValue());
		}
		rule.setName((String)source.get("name"));
		rule.setActive(Boolean.parseBoolean(source.getOrDefault("active", "false").toString()));
		return rule;
	}

}