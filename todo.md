#TODO

## Submit to the @Autowire
JpaRepository autowiring is far easier than implementing it by hand or doing the xml approach.
Go back and autowire everything :(

## Object reuse optimizations
it.eng.cepmiddleware.api.v1.engine.GetEnginesService
it.eng.cepmiddleware.api.v1.engine.GetEngineService

## Reconsider design choice
/CEP-middleware/src/main/java/it/eng/cepmiddleware/Service.java {
	Is it a good idea to use Object varargs in an interface?
}
/CEP-middleware/src/main/java/it/eng/cepmiddleware/CEPEngine.java {
	Reconsider the visitor approach. Generally the same implementation goes for each sub-type.
	static method and abstract class approach don't look good.
}
