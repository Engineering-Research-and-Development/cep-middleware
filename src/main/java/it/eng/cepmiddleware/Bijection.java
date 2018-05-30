package it.eng.cepmiddleware;

public interface Bijection<Target, Source> {

	public Target execute(Source source) throws Exception;
	public Source invert(Target target) throws Exception;

}
