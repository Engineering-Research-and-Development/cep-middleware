package it.eng.cepmiddleware;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CEPPayloadMaker implements CEPEngineVisitor {

	@Override
	public Object visit(CEPEngine engine) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> result = mapper.convertValue(engine, Map.class);
		result.remove("rules");
		result.put("allRules", "/rules");
		result.put("singleRule", "/rules/{ruleId}");
		return result;
	}

}
