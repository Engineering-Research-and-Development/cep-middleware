package it.eng.cepmiddleware.rule;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PerseoFERule extends Rule {
	
	private String action;
	
	public PerseoFERule() {}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
