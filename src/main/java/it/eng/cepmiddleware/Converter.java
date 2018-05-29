package it.eng.cepmiddleware;

public interface Converter<Target, Source> {

	Target convert(Source source);

}
