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
import it.eng.cepmiddleware.rule.PerseoRule;

public class Perseo implements CEPEngine {

	private static String[] supportedEventTypes= {"iotEvent"};
	private String engineId;
	private PerseoWebService service;
	private PerseoRuleConverter ruleConverter;
	@Autowired private PerseoRuleRepository repository;

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
				repository.save(rule);
				ResponseEntity ruleCreation = service.createRule(rule);
				if (ruleCreation.getStatusCode().is2xxSuccessful()) {
					return ResponseEntity.<PlainResponseBody>ok(
						new PlainResponseBody("Rule successfully created and activated")
					);
				} else {
					rule.setActive(false);
					repository.save(rule);
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
				repository.save(rule);
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
		PerseoRule rule = repository.getRuleById(ruleId);
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
		repository.count();
		return new ResponseEntity<>(repository.getRulesByOwner(this.engineId), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteRule(String ruleId) {
		PerseoRule rule = repository.getRuleById(ruleId);
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
		repository.delete(rule);
		return ResponseEntity.<PlainResponseBody>ok(
			new PlainResponseBody("Rule successfully deleted")
		);
	}

	@Override
	public ResponseEntity<?> updateRule(String ruleId, Map<String, Object> ruleMap) {
		PerseoRule newRule = ruleConverter.convert(ruleMap);
		PerseoRule oldRule = repository.getRuleById(ruleId);
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
		Collection<String> ruleIds = repository.getAllRuleIdsOfOwner(this.engineId);
		ruleIds.stream().forEach(
			(ruleId) -> this.deleteRule(ruleId)
		);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<?> getSupportedEventTypes() {
		return new ResponseEntity(
			Perseo.supportedEventTypes,
			HttpStatus.OK
		);
	}

}
