package it.eng.cepmiddleware.engine.perseo_core;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import it.eng.cepmiddleware.CRUDService;
import it.eng.cepmiddleware.Converter;
import it.eng.cepmiddleware.rule.Rule;

import java.util.stream.Collectors;

import it.eng.cepmiddleware.engine.CEPEngine;

public class PerseoCore implements CEPEngine {
	
	@Autowired MiddlewarePerseoCoreRuleCRUDService middlewareCRUDService;
	private String hostUrl;
	private String name;
	private PerseoCoreRuleCRUDService perseoCoreCRUD;

	public PerseoCore(String name, String hostUrl) {
		this.hostUrl = hostUrl;
		this.perseoCoreCRUD = new PerseoCoreRuleCRUDService(this.hostUrl);
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public String getHostUrl() {
		return hostUrl;
	}
	
	public ResponseEntity<?> createRule(Rule rule) {
		UniformToNativePerseoCoreRuleAdapter adaptedRule = new UniformToNativePerseoCoreRuleAdapter(rule);
		try {
			perseoCoreCRUD.create(adaptedRule);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.METHOD_NOT_ALLOWED);
		}
	}
	
	public ResponseEntity<?> getRule(String ruleId) {
		return perseoCoreCRUD.read(ruleId)
			.<Rule>map((nativeRule) -> {
				return new NativeToUniformPerseoCoreRuleAdapter(nativeRule);
			})
			.<ResponseEntity<Rule>>map((rule) -> {
				return new ResponseEntity<Rule>(rule, HttpStatus.OK);
			})
			.orElseGet(() -> ResponseEntity.badRequest().build());
	}
	
	public ResponseEntity<?> getRules() {
		List result = perseoCoreCRUD.read().stream()
			.<Rule>map((nativeRule) -> {
				return new NativeToUniformPerseoCoreRuleAdapter(nativeRule);
			})
			.collect(Collectors.toList());
		if (result.isEmpty()) {
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> updateRule(Rule rule) {
		PerseoCoreNativeRule adaptedRule = new UniformToNativePerseoCoreRuleAdapter(rule);
		try {
			perseoCoreCRUD.update(adaptedRule);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.METHOD_NOT_ALLOWED);
		}
	}

	@Override
	public ResponseEntity<?> deleteRule(String ruleId) {
		try {
			perseoCoreCRUD.delete(ruleId);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.METHOD_NOT_ALLOWED);
		}
	}

	@Override
	public ResponseEntity<?> postEvent(Object event) {
		try {
			HttpResponse response = Unirest.post(hostUrl + "/perseo-core/events")
				.header("content-type", "application/json")
				.body(event)
				.asJson();
			return new ResponseEntity<>(
				HttpStatus.valueOf(response.getStatus())
			);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@Override
	public Converter<? extends Rule, Map<String, Object>> getRuleConverter() {
		return new PerseoCoreRuleConverter(this.getName());
	}

	@Override
	public CRUDService getMiddlewareCRUD() throws Exception {
		return middlewareCRUDService;
	}
	
}
