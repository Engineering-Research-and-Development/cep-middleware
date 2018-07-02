package it.eng.cepmiddleware.rule;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;

import it.eng.cepmiddleware.HashidsComponent;

@Entity(name = "Rule")
public class Rule {

	public static final String defaultRuleId = "initial";

	private HashidsComponent hashids;

	private Long id;
	
	private String ruleId = Rule.defaultRuleId;

	private String description;
	
	private String statement;

	private String owner;
	
	public Rule() {
		this.hashids = new HashidsComponent();
	}

	@Id
	@GeneratedValue
	protected Long getId() {
		return id;
	}

	protected void setId(Long id) {
		this.id = id;
		try {
			this.ruleId = hashids.execute(id);
		} catch (Exception e) {
			System.out.println("hashids.execute got rekt");
		}
	}

	public String getRuleId() {
		return this.ruleId;
	}
	
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
		try {
			this.id = hashids.invert(ruleId);
		} catch (Exception e) {
			System.out.println("hashids.invert got rekt");
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(nullable = false)
	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	@Column(nullable = false)
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return
			"Rule [hashids=" + hashids +
			", id=" + id +
			", ruleId=" + ruleId +
			", description=" + description +
			", statement=" + statement +
			", owner=" + owner + "]"
		;
	}
	
}
