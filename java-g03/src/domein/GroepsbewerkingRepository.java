package domein;

import java.util.*;

public class GroepsbewerkingRepository {

	private Map<String, Groepsbewerking> groepsbewerkingen;

	public GroepsbewerkingRepository() {
		this.groepsbewerkingen = new HashMap<>();
	}
	public Map<String, Groepsbewerking> geefGroepsBewerkingen() {
		return groepsbewerkingen;
	}

	public Groepsbewerking geefGroepsBewerking(String naam) {
        if (!this.groepsbewerkingen.containsKey(naam)) {
            throw new IllegalArgumentException("Deze bewerking bestaat niet");
        }
        return groepsbewerkingen.get(naam);
    }
}