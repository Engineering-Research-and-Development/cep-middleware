package it.eng.cepmiddleware;

import org.hashids.Hashids;
import org.springframework.stereotype.Component;

@Component
public class HashidsComponent implements Bijection<String, Long> {

	private Hashids hashids;

	public HashidsComponent() {
		this("omaeWaMouShindeiru");
	}

	public HashidsComponent(String salt) {
		this.hashids = new Hashids(salt, 8);
	}

	@Override
	public String execute(Long source) throws Exception {
		return hashids.encode(source);
	}

	@Override
	public Long invert(String target) throws Exception {
		return hashids.decode(target)[0];
	}

}
