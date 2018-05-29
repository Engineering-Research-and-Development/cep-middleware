package it.eng.cepmiddleware;

import org.hashids.Hashids;
import org.springframework.stereotype.Component;

@Component
public class HashidsComponent implements Converter<String, Integer> {
	
	private Hashids hashids;

	public HashidsComponent() {
		this("omaeWaMouShindeiru");
	}
	
	public HashidsComponent(String salt) {
		this.hashids = new Hashids(salt);
	}

	@Override
	public String convert(Integer source) {
		return hashids.encode(source);
	}
	
	public String convert(Long source) {
		return hashids.encode(source);
	}

}
