package it.eng.cepmiddleware.engine;

public class PerseoFERule {

	private String name;
	private String text;
	private Object action;
	
	public PerseoFERule(CEPRule rule) {
		this.name = rule.getId();
		this.text = rule.getStatement();
		this.action = rule.getAction();
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Object getAction() {
		return action;
	}

	public void setAction(Object action) {
		this.action = action;
	}

	public String getName() {
		return name;
	}

}
