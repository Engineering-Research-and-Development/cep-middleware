package it.eng.cepmiddleware.rule;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;

import it.eng.cepmiddleware.HashidsComponent;

@Entity(name = "Rule")
public class Rule {

	private HashidsComponent hashids;

	private Long id;
	private String ruleId;
	private String owner;
	private boolean active;
	
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

	@Column(nullable = false)
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return
			"Rule [hashids=" + hashids +
			", id=" + id +
			", ruleId=" + ruleId +
			", owner=" + owner + "]"
		;
	}
	
}
