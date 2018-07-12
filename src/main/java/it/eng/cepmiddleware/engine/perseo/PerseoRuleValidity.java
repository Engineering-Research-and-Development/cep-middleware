package it.eng.cepmiddleware.engine.perseo;

public class PerseoRuleValidity {

	private boolean valid;
	private String message;

	PerseoRuleValidity(
		boolean valid,
		String message
	) {
		this.valid = valid;
		this.message = message;
	}

	public boolean isValid() {
		return valid;
	}

	public String getMessage() {
		return message;
	}

	private static PerseoRuleValidity validPrototype = new PerseoRuleValidity(true, "Valid");
	public static PerseoRuleValidity valid() {
		return validPrototype;
	}

}
