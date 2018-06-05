package it.eng.cepmiddleware.engine.perseo_core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import it.eng.cepmiddleware.CRUDService;

public class PerseoCoreRuleCRUDService implements CRUDService<PerseoCoreNativeRule, String> {
	
	private String hostUrl;
	
	public PerseoCoreRuleCRUDService(String hostUrl) {
		this.hostUrl = hostUrl;
	}

	@Override
	public void create(PerseoCoreNativeRule rule) throws Exception {
		HttpResponse<PerseoCoreNativeRule> response = Unirest.post(hostUrl + "/perseo-core/rules")
			.header("accept", "application/json")
			.body(rule)
			.asObject(PerseoCoreNativeRule.class);
		if (response.getStatus()/100 != 2) {
			throw new Exception("Creation failed.");
		}
	}

	@Override
	public Optional<PerseoCoreNativeRule> read(String ruleId) {
		HttpResponse<PerseoCoreNativeRule> response;
		try {
			response = Unirest.get(hostUrl + "/perseo-core/rules/" + ruleId)
				.header("accept", "application/json")
				.asObject(PerseoCoreNativeRule.class);
		} catch (UnirestException e) {
			return Optional.empty();
		}
		return Optional.ofNullable(response.getBody());
	}

	@Override
	public Collection<PerseoCoreNativeRule> read() {
		HttpResponse<PerseoCoreNativeRule[]> response;
		try {
			response = Unirest.get(hostUrl + "/perseo-core/rules")
				.header("accept", "application/json")
				.asObject(PerseoCoreNativeRule[].class);
		} catch (UnirestException e) {
			return new ArrayList<PerseoCoreNativeRule>();
		}
		return Arrays.asList(response.getBody());
	}

	@Override
	public void update(PerseoCoreNativeRule newRule) throws Exception {
		try {
			read(newRule.getName()).get();
		} catch (NoSuchElementException e) {
			throw new Exception("Rule doesn't exist");
		}
		try {
			create(newRule);
		} catch (Exception e) {
			throw new Exception("Rule couldn't be updated");
		}
	}

	@Override
	public void delete(String ruleId) throws Exception {
		HttpResponse<PerseoCoreNativeRule> response = Unirest.delete(hostUrl + "/perseo-core/rules/" + ruleId)
			.header("accept", "application/json")
			.asObject(PerseoCoreNativeRule.class);
		if (response.getStatus()/100 != 2) {
			throw new Exception("Deletion failed.");
		}
	}

}
