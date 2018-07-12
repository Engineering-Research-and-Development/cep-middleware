package it.eng.cepmiddleware.engine.perseo;

public class PerseoResponse {

	private PerseoNativeRule data;
	private String error;

	public PerseoNativeRule getData() {
		return data;
	}

	public void setData(PerseoNativeRule data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "PerseoResponse [data=" + data + ", error=" + error + "]";
	}

}
