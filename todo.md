#TODO

## Submit to the @Autowire
JpaRepository autowiring is far easier than implementing it by hand or doing the xml approach.
Go back and autowire everything :(

## Object reuse optimizations
it.eng.cepmiddleware.api.v1.engine.GetEnginesService
it.eng.cepmiddleware.api.v1.engine.GetEngineService
it.eng.cepmiddleware.rule.Rule << Hashids

## Reconsider design choice
it.eng.cepmiddleware {
	Is it a good idea to use Object varargs in an interface?
}
it.eng.cepmiddleware.engine.CEPEngine {
	Reconsider the visitor approach. Generally the same implementation goes for each sub-type.
	static method and abstract class approach don't look good.
	Conclusion: Visitor ditched :)
}

## Bugs
it.eng.cepmiddleware.rule {
	I am quite sure partial deletions (deleted in db but not engine) will pose a problem in the future...
}
it.eng.cepmiddleware.engine.PerseoCore {
	updateRule will definitely be a problem, must fix
}
it.eng.cepmiddleware.api.v1.engine.rules.ToggleRuleService {
	There's definitely gonna be an issue here. Check it out in the future.
}
com.fasterxml.jackson.core {
	Things that work with json dont work with xml requests
}
PUT rule/{ruleId} {
	After doing an unsuccesful PUT ona rule, no other rule will be detected. This has to do something with the intermediate state.
}