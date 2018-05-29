package it.eng.cepmiddleware.engine;

import java.util.Map;

import it.eng.cepmiddleware.Converter;
import it.eng.cepmiddleware.rule.Rule;

public class PerseoCoreConverter implements Converter<Rule, Map<String, Object>> {
	
	private String ownerEngineName;

	PerseoCoreConverter(String ownerEngineName) {
		this.ownerEngineName = ownerEngineName;
	}

	@Override
	public Rule convert(Map<String, Object> source) {
		Rule rule = new Rule();
		rule.setOwner(ownerEngineName);
		rule.setDescription((String)source.get("description"));
		rule.setStatement((String)source.get("statement"));
		return rule;
	}

}