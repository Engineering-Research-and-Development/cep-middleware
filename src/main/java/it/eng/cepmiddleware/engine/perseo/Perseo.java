package it.eng.cepmiddleware.engine.perseo;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import it.eng.cepmiddleware.engine.CEPEngine;
import it.eng.cepmiddleware.responses.CepInclusiveResponseBody;
import it.eng.cepmiddleware.responses.PlainResponseBody;
import it.eng.cepmiddleware.rule.PerseoRuleRepository;
import it.eng.cepmiddleware.rule.ArchivedPerseoRule;
import it.eng.cepmiddleware.rule.ArchivedPerseoRuleRepository;
import it.eng.cepmiddleware.rule.PerseoRule;

public class Perseo implements CEPEngine {

	private static String[] supportedEventTypes= {"iotEvent"};
	private String engineId;
	private PerseoWebService service;
	private PerseoRuleConverter ruleConverter;
	@Autowired private PerseoRuleRepository perseoRuleRepository;
	@Autowired private ArchivedPerseoRuleRepository archivedPerseoRuleRepository;

	public Perseo(String engineId, String hostUrl) {
		this.engineId = engineId;
		this.service = new PerseoWebService(hostUrl);
		this.ruleConverter = new PerseoRuleConverter(engineId);
	}

	@Override
	public ResponseEntity<?> createRule(Map<String, Object> ruleMap) {
		PerseoRule rule = ruleConverter.convert(ruleMap);
		return this.createRule(rule);
	}
	
	public ResponseEntity<?> createRule(PerseoRule rule) {
		PerseoRuleValidity ruleValidity = service.checkRuleValidity(rule);
		if (ruleValidity.isValid()) {
			if (rule.isActive()) {
				perseoRuleRepository.save(rule);
				ResponseEntity ruleCreation = service.createRule(rule);
				if (ruleCreation.getStatusCode().is2xxSuccessful()) {
					return ResponseEntity.<PlainResponseBody>ok(
						new PlainResponseBody("Rule successfully created and activated")
					);
				} else {
					rule.setActive(false);
					perseoRuleRepository.save(rule);
					return ResponseEntity.status(HttpStatus.CREATED)
						.<CepInclusiveResponseBody>body(
							new CepInclusiveResponseBody(
								"Rule successfully created but couldn't activate",
								ruleCreation.getBody().toString()
							)
						)
					;
				}
			}
			else {
				perseoRuleRepository.save(rule);
				return ResponseEntity.<PlainResponseBody>ok(
					new PlainResponseBody("Rule successfully created")
				);
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.<CepInclusiveResponseBody>body(
					new CepInclusiveResponseBody(
						"Rule is invalid or conflicts with targeted engine",
						ruleValidity.getMessage().toString()
					)
				)
			;
		}
	}

	@Override
	public ResponseEntity<?> getRule(String ruleId) {
		PerseoRule rule = perseoRuleRepository.getRuleById(ruleId);
		if (rule == null) {
			return ResponseEntity.notFound().build();
		}
		if (rule.getOwner().equals(this.engineId)) {
			return new ResponseEntity<>(rule, HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<?> getRules() {
		return new ResponseEntity<>(perseoRuleRepository.getRulesByOwner(this.engineId), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteRule(String ruleId) {
		PerseoRule rule = perseoRuleRepository.getRuleById(ruleId);
		if (rule == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.<PlainResponseBody>body(
					new PlainResponseBody(
						String.format("Rule %s does not exist in %s engine", ruleId, this.engineId)
					)
				)
			;
		}
		if (rule.isActive()) {
			service.deleteRule(rule.getName());
		}
		perseoRuleRepository.delete(rule);
		return ResponseEntity.<PlainResponseBody>ok(
			new PlainResponseBody("Rule successfully deleted")
		);
	}

	@Override
	public ResponseEntity<?> updateRule(String ruleId, Map<String, Object> ruleMap) {
		PerseoRule newRule = ruleConverter.convert(ruleMap);
		PerseoRule oldRule = perseoRuleRepository.getRuleById(ruleId);
		if (oldRule == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.<PlainResponseBody>body(
					new PlainResponseBody(
						String.format("Rule %s does not exist in %s engine", ruleId, this.engineId)
					)
				)
			;
		}
		PerseoRuleValidity newRuleValidity = service.checkRuleValidity(newRule);
		if (!newRuleValidity.isValid()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.<CepInclusiveResponseBody>body(
					new CepInclusiveResponseBody(
						"Rule is invalid or conflicts with targeted engine",
						newRuleValidity.getMessage()
					)
				)
			;
		}
		if (oldRule.isActive()) {
			service.deleteRule(oldRule.getName());
		}
		newRule.setRuleId(oldRule.getRuleId());
		ResponseEntity newRuleCreation = this.createRule(newRule);
		switch (newRuleCreation.getStatusCodeValue()) {
			case 200: return ResponseEntity.<PlainResponseBody>ok(
				new PlainResponseBody(
					newRule.isActive()?
						"Rule successfully updated and activated":
						"Rule successfully updated"
				)
			); 
			case 201: return new ResponseEntity(new CepInclusiveResponseBody(
				"Rule successfully updated but couldn't activate",
				newRuleCreation.getBody().toString()
			), HttpStatus.CREATED);
			default: return ResponseEntity.<PlainResponseBody>ok(
				new PlainResponseBody(newRuleCreation.getBody().toString())
			);
		}
	}

	@Override
	public ResponseEntity<?> deleteAllRules() {
		Collection<String> ruleIds = perseoRuleRepository.getAllRuleIdsOfOwner(this.engineId);
		ruleIds.stream().forEach(
			(ruleId) -> this.deleteRule(ruleId)
		);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<?> deleteAllRules(boolean archiveRules) {
		if (false == archiveRules) {
			return deleteAllRules();
		} else {
			perseoRuleRepository.getRulesByOwner(this.engineId).forEach(
				(perseoRule) -> {
					archivedPerseoRuleRepository.save(new ArchivedPerseoRule(perseoRule));
					Collection<String> ruleIds = perseoRuleRepository.getAllRuleIdsOfOwner(this.engineId);
					ruleIds.stream().forEach(
						(ruleId) -> this.deleteRule(ruleId)
					);
				}
			);
			return ResponseEntity.ok().build();
		}
	}

	@Override
	public ResponseEntity<?> getSupportedEventTypes() {
		return new ResponseEntity(
			Perseo.supportedEventTypes,
			HttpStatus.OK
		);
	}

}
