package it.eng.cepmiddleware.rule;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import it.eng.cepmiddleware.ObjectMapperProvider;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PerseoFERule extends Rule {
	
	private String action;
	private String name;

	public PerseoFERule() {}

	public String getAction() {
		return action;
	}

	public void setAction(Map action) {
		this.action = ObjectMapperProvider
			.getObjectMapper()
			.writeValue(action);
	}
	
	public void setAction(String action) {
		this.action = action;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name != null) {
			this.name = name;
		} else {
			this.name = this.getRuleId();
		}
	}

}
