#TODO

## Submit to the @Autowire
JpaRepository autowiring is far easier than implementing it by hand or doing the xml approach.
Go back and autowire everything :(

## Object reuse optimizations
it.eng.cepmiddleware.api.v1.engine.GetEnginesService
it.eng.cepmiddleware.api.v1.engine.GetEngineService
/CEP-middleware/src/main/java/it/eng/cepmiddleware/rule/Rule.java << Hashids

## Reconsider design choice
/CEP-middleware/src/main/java/it/eng/cepmiddleware/Service.java {
	Is it a good idea to use Object varargs in an interface?
}
/CEP-middleware/src/main/java/it/eng/cepmiddleware/CEPEngine.java {
	Conclusion: Visitor ditched :)
}

## Bugs
it.eng.cepmiddleware.rule {
	I am quite sure partial deletions (deleted in db but not engine) will pose a problem in the future...
}
it.eng.cepmiddleware.engine.PerseoCore {
	updateRule will definitelly be a problem, must fix
}