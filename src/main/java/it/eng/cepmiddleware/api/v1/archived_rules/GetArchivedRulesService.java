package it.eng.cepmiddleware.api.v1.archived_rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.eng.cepmiddleware.Service;
import it.eng.cepmiddleware.responses.PlainResponseBody;
import it.eng.cepmiddleware.rule.ArchivedRuleRepository;

@org.springframework.stereotype.Service
public class GetArchivedRulesService implements Service {

	@Autowired private ArchivedRuleRepository archivedRuleRepository;
	@Autowired private EngineTypeToArchivedRuleRepositoryService engineTypeToArchivedRuleRepositoryService;

	@Override
	public ResponseEntity<?> execute(Object... parameters) {
		if (parameters[0] instanceof String) {
			String type = (String) parameters[0];
			return getArchivedRules(type);
		} else if (parameters[0] == null) {
			return ResponseEntity.ok(archivedRuleRepository.findAll());
		}
		return Service.paramError;
	}

	private ResponseEntity<?> getArchivedRules(String type) {
		ArchivedRuleRepository archivedRuleRepository = engineTypeToArchivedRuleRepositoryService.getRepository(type);
		if (archivedRuleRepository == null) {
			return new ResponseEntity(
				new PlainResponseBody(String.format("%s engine type is not supported", type)),
				HttpStatus.NOT_FOUND
			);
		}
		return ResponseEntity.ok(archivedRuleRepository.findAll());
	}

}
