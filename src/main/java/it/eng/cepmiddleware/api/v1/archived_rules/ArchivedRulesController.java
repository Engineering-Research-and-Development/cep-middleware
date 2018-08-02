package it.eng.cepmiddleware.api.v1.archived_rules;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArchivedRulesController implements ArchivedRulesApi {

	@Autowired private GetArchivedRulesService getArchivedRulesService;
	@Autowired private GetArchivedRuleService getArchivedRuleService;
	@Autowired private ReassignArchivedRuleService reassignArchivedRuleService;

	@Override
	public ResponseEntity<?> getArchivedRules(@RequestParam(required=false) String type) {
		return getArchivedRulesService.execute(type);
	}

	@Override
	public ResponseEntity<?> getArchivedRule(@PathVariable("ruleId") String ruleId) {
		return getArchivedRuleService.execute(ruleId);
	}

	@Override
	public ResponseEntity<?> reassignArchivedRule(
		@PathVariable("ruleId") String ruleId,
		@Valid @RequestBody ArchivedRuleReassignment reassignment
	) {
		return reassignArchivedRuleService.execute(ruleId, reassignment);
	}

}
