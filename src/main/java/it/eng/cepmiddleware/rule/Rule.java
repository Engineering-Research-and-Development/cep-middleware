package it.eng.cepmiddleware.rule;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "Rule")
public class Rule {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false, unique=true)
	private String ruleId;
	
	private String description;
	
	@Column(nullable = false)
	private String statement;

	@Column(nullable = false)
	private String owner;
	
	public Rule() {}

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
}
