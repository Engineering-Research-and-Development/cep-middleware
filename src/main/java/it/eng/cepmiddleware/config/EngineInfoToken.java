package it.eng.cepmiddleware.config;

public class EngineInfoToken {

	private String engineId;
	private String engineType;
	private String hostUrl;

	public EngineInfoToken() {}
	
	public EngineInfoToken(ImmutableEngineInfoToken token) {
		this.engineId = token.getEngineId();
		this.engineType = token.getEngineType();
		this.hostUrl = token.getHostUrl();
	}

	public String getEngineId() {
		return engineId;
	}

	public void setEngineId(String engineId) {
		this.engineId = engineId;
	}

	public String getEngineType() {
		return engineType;
	}

	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}

	public String getHostUrl() {
		return hostUrl;
	}

	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}

	public ImmutableEngineInfoToken getImmutable() {
		return new ImmutableEngineInfoToken(this);
	}

}