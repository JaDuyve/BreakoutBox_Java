package domein;

import persistentie.OefeningMapper;

import java.util.*;

public class OefeningRepository {

	private Map<String, Oefening> oefeningen;

	private OefeningMapper oefeningMapper;



	public OefeningRepository() {
		this.oefeningMapper = new OefeningMapper();
		this.oefeningen = oefeningMapper.geefOefeningen();
	}

    public Map<String, Oefening> geefOefeningen(){
		return  oefeningen;
    }
	public Oefening geefOefening(String naam) {
		if (!this.oefeningen.containsKey(naam)) {
			throw new IllegalArgumentException("Deze oefening bestaat niet");
		}
		return oefeningen.get(naam);
	}
    /**
     *
     * @param feedback
	 * @param naam
	 * @param feedback
	 * @param antwoord
	 * @param opgave
     */
    public void voegOefeningToe(String naam, String feedback, String antwoord, String opgave) {
    	controleerOpNaamMap(naam);
        this.oefeningen.put(naam, new Oefening(naam, feedback, antwoord, opgave));
        // TODO - Databank ToevoegenShizzle
    }

	/**
	 *
	 * @param oefeningNaam
	 * @param naam
	 * @param feedback
	 * @param antwoord
	 * @param opgave
	 */
	public void wijzigOefening(String oefeningNaam, String naam, String feedback, String antwoord, String opgave) {
		if (oefeningNaam.equals(naam)){
			this.oefeningen.replace(oefeningNaam, new Oefening(naam, feedback, antwoord, opgave));

		}else {

			this.oefeningen.remove(oefeningNaam);
			controleerOpNaamMap(naam);
			this.oefeningen.put(naam, new Oefening(naam, feedback, antwoord, opgave));

		}
	}
	private boolean controleerOpNaamMap(String naam) {
		return this.oefeningen.containsKey(naam);
	}
    /**
     *
     * @param oefening
     */
    public void verwijderOefening(Oefening oefening) {
        this.oefeningen.remove(oefening);
    }

    /*
     * Deze constructor word door de testen gebruikt om de dummy te instantiëren
     */
	public OefeningRepository(OefeningMapper oefeningMapper) {
		this.oefeningMapper = oefeningMapper;
	}
}