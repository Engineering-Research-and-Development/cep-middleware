package it.eng.cepmiddleware.responses;

public class CepInclusiveResponseBody extends PlainResponseBody {

	private String cepMessage;

	public CepInclusiveResponseBody(String message, String cepMessage) {
		super(message);
		this.cepMessage = cepMessage;
	}

	public String getCepMessage() {
		return this.cepMessage;
	}

	@Override
	public String toString() {
		return cepMessage;
	}

}
