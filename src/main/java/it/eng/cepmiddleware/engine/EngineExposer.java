package it.eng.cepmiddleware.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EngineExposer {

	@Autowired private CEPEngineProvider engineFactory;
	@Autowired private EngineInfoTokenRepository engineInfoTokenRepository;
	private EngineInfoToken exposedEngineInfo;
	private CEPEngine exposedEngine = new ErrorCEPEngine();

	public boolean setEngineToExpose(String engineId) {
		CEPEngine engine = engineFactory.getCEPEngine(engineId);
		if (engine instanceof ErrorCEPEngine) {
			return false;
		}
		this.exposedEngine = engine;
		this.exposedEngineInfo = engineInfoTokenRepository.getOne(engineId);
		return true;
	}

	public CEPEngine getExposedEngine() {
		return this.exposedEngine;
	}

	public EngineInfoToken getExposedEngineInfo() {
		return this.exposedEngineInfo;
	}

}
