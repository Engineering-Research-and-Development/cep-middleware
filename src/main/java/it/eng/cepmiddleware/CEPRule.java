package it.eng.cepmiddleware;

public class CEPRule {
	
	private String id;
	private String statement;
	private String action;
	private Boolean active;
	
	public CEPRule(String id) {
		this.id = id;
		this.statement = "";
		this.action = "";
		this.active = true;
	}
	
	public CEPRule(String id, String statement) {
		this.id = id;
		this.statement = statement;
		this.action = "";
		this.active = true;
	}
	
	public CEPRule(String id, String statement, String action) {
		this.id = id;
		this.statement = statement;
		this.action = action;
		this.active = true;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getId() {
		return id;
	}
	
}
