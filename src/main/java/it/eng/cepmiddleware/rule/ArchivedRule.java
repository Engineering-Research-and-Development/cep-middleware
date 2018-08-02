package it.eng.cepmiddleware.rule;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import it.eng.cepmiddleware.HashidsComponent;

@Entity(name = "ArchivedRule")
public abstract class ArchivedRule {

	private HashidsComponent hashids;

	private Long id;
	private String ruleId;

	public ArchivedRule() {
		this.hashids = new HashidsComponent();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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

	@Override
	public String toString() {
		return
			"ArchivedRule [hashids=" + hashids +
			", id=" + id +
			", ruleId=" + ruleId + "]"
		;
	}

	public abstract Rule extract();

}
