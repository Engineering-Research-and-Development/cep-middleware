package it.eng.cepmiddleware.api.v1.archived_rules;

public class ArchivedRuleReassignment {

	private String newOwner;
	private boolean active;

	public ArchivedRuleReassignment() {}

	public String getNewOwner() {
		return newOwner;
	}

	public void setNewOwner(String newOwner) {
		this.newOwner = newOwner;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
