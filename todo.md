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
	"Problem": "Is it a good idea to use Object varargs in an interface?"
}
```
- [x] it.eng.cepmiddleware.engine.CEPEngine
```
{
	"Problem": "Reconsider the visitor approach. Generally the same implementation goes for each sub-type. static method and abstract class approach don't look good.",
	"Conclusion": "Visitor ditched :)"
}
```

## Bugs
- [ ] it.eng.cepmiddleware.rule
```
{
	"Problem": "I am quite sure partial deletions (deleted in db but not engine) will pose a problem in the future..."
}
```
- [ ] it.eng.cepmiddleware.engine.PerseoCore
```
{
	"Problem": "updateRule will definitely be a problem, must fix"
}
```
- [ ] it.eng.cepmiddleware.api.v1.engine.rules.ToggleRuleService
```
{
	"Problem": "There's definitely gonna be an issue here. Check it out in the future."
}
```
- [ ] com.fasterxml.jackson.core
```
{
	"Problem": "Things that work with json dont work with xml requests"
}
```
- [x] PUT rule/{ruleId}
```
{
	"Problem": "After doing an unsuccesful PUT on a rule, no other rule will be detected. This has to do something with the intermediate state.",
	"Conclusion": "This was an issue with "
}
```