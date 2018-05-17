package it.eng.cepmiddleware;

public class PerseoCoreRule {
	
	private String name;
	private String text;

	public PerseoCoreRule(CEPRule rule) {
		this.name = rule.getId();
		this.text = rule.getStatement();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
