# TODO

## Submit to the @Autowire
JpaRepository autowiring is far easier than implementing it by hand or doing the xml approach.
Go back and autowire everything :(

## Object reuse optimizations
- [ ] it.eng.cepmiddleware.api.v1.engine.GetEnginesService
- [ ] it.eng.cepmiddleware.api.v1.engine.GetEngineService
- [ ] it.eng.cepmiddleware.rule.Rule << Hashids

## Reconsider design choice
- [ ] it.eng.cepmiddleware
```
{
	"problem": "Is it a good idea to use Object varargs in an interface?"
}
```
- [x] it.eng.cepmiddleware.engine.CEPEngine
```
{
	"problem": "Reconsider the visitor approach. Generally the same implementation goes for each sub-type. static method and abstract class approach don't look good.",
	"conclusion": "Visitor ditched :)"
}
```

## Bugs
- [ ] it.eng.cepmiddleware.rule
```
{
	"problem": "I am quite sure partial deletions (deleted in db but not engine) will pose a problem in the future..."
}
```
- [ ] it.eng.cepmiddleware.engine.PerseoCore
```
{
	"problem": "updateRule will definitely be a problem, must fix"
}
```
- [ ] it.eng.cepmiddleware.api.v1.engine.rules.ToggleRuleService
```
{
	"problem": "There's definitely gonna be an issue here. Check it out in the future."
}
```
- [ ] com.fasterxml.jackson.core
```
{
	"problem": "Things that work with json dont work with xml requests"
}
```
- [x] PUT rule/{ruleId}
```
{
	"problem": "After doing an unsuccesful PUT on a rule, no other rule will be detected. This has to do something with the intermediate state.",
	"conclusion": "This was an issue with perseo-core and perseo-fe update implementations. Once those were dealt with, the bug disappeared."
}
```
- [x] it.eng.cepmiddleware.api.v1.engine.EngineAdapter
```
{
	"problem": "engineId is rendered as engineName",
	"conclusion": "Fixed",
	"foundBy": {
		"name": "Valsidalv",
		"link": "https://github.com/Valsidalv"
	}
}
```