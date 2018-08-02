package it.eng.cepmiddleware.api.v1.archived_rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.eng.cepmiddleware.rule.ArchivedPerseoRuleRepository;
import it.eng.cepmiddleware.rule.ArchivedRuleRepository;

@Component
public class EngineTypeToArchivedRuleRepositoryService {

	@Autowired private ArchivedPerseoRuleRepository archivedPerseoRuleRepository;

	public ArchivedRuleRepository getRepository(String engineType) {
		String lowercaseEngineType = engineType.toLowerCase();
		switch (lowercaseEngineType) {
			case "perseo": return archivedPerseoRuleRepository;
			default: return null;
		}
	}

}
