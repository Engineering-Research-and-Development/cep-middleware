package it.eng.cepmiddleware.rule;

import java.util.Map;

public interface RuleBuilder {

	Rule build(Map<String, Object> map) throws Exception;

}
