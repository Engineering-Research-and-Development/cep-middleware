package it.eng.cepmiddleware.engine;

public class CEPRule {
	
	private String id;
	private String statement;
	private Object action;
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
	
	public CEPRule(String id, String statement, Object action) {
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

	public Object getAction() {
		return action;
	}

	public void setAction(Object action) {
		this.action = action;
	}

	public String getId() {
		return id;
	}
	
}
