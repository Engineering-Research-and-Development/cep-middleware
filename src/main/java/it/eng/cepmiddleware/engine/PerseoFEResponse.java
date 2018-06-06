package it.eng.cepmiddleware.engine;

public class PerseoFEResponse {

	private PerseoFrontEndNativeRule data;
	private String error;

	public PerseoFrontEndNativeRule getData() {
		return data;
	}

	public void setData(PerseoFrontEndNativeRule data) {
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
		return "PerseoFEResponse [data=" + data + ", error=" + error + "]";
	}

}
