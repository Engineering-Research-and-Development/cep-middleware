package it.eng.cepmiddleware.config;

public class ImmutableEngineInfoToken {

	private String engineId;
	private String engineType;
	private String hostUrl;

	public ImmutableEngineInfoToken(EngineInfoToken token) {
		this.engineId = token.getEngineId();
		this.engineType = token.getEngineType();
		this.hostUrl = token.getHostUrl();
	}

	public String getEngineId() {
		return engineId;
	}

	public String getEngineType() {
		return engineType;
	}

	public String getHostUrl() {
		return hostUrl;
	}

	@Override
	public int hashCode() {
		return engineId.concat(engineType).concat(hostUrl).hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof ImmutableEngineInfoToken)) return false;
		ImmutableEngineInfoToken other = (ImmutableEngineInfoToken)obj;
		return (
			this.engineId.equals(other.getEngineId()) &&
			this.engineType.equals(other.getEngineType()) &&
			this.hostUrl.equals(other.getHostUrl())
		);
	}
}
