package domein;

import java.util.*;

public class VakRepository {

	private Map<String, Vak> vakken;

	public VakRepository() {
		this.vakken = new HashMap<>();
		// TODO - Mapper Uit de DB halen!!! xoxoxo
	}

	public Map<String, Vak> geefVakken() {
		return vakken;
	}

	public Vak geefVak(String naam) {
		if (!this.vakken.containsKey(naam)) {
			throw new IllegalArgumentException("Dit vak bestaat niet");
		}
		return vakken.get(naam);
	}
}