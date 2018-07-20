package it.eng.cepmiddleware.engine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineInfoTokenRepository extends JpaRepository<EngineInfoToken, String> {}
