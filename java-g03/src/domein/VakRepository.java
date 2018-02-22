package domein;

import persistentie.VakMapper;

import java.util.*;

public class VakRepository {

	private Map<String, Vak> vakken;
    private VakMapper vakMapper;

	public VakRepository() {
        this.vakMapper = new VakMapper();
		this.vakken = vakMapper.geefVakken();
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