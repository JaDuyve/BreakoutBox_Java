package domein;

import persistentie.OefeningMapper;

import java.util.*;

public class OefeningRepository {

	private List<Oefening> oefeningen;

	private OefeningMapper oefeningMapper;



	public OefeningRepository() {
		this.oefeningMapper = new OefeningMapper();
		this.oefeningen = oefeningMapper.geefOefeningen();
	}

    public List<Oefening> geefOefeningen(){
		return  oefeningen;
    }

	public Oefening geefOefening(String naam) {
	    Oefening oefening = oefeningen.stream().filter(o -> o.getNaam().equals(naam)).findFirst().get();
		if (!this.oefeningen.contains(naam)) {
			throw new IllegalArgumentException("Deze oefening bestaat niet");
		}
		return oefening;
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
        // this.oefeningen.put(naam, new Oefening(naam, feedback, antwoord, opgave));
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
		/**if (oefeningNaam.equals(naam)){
			this.oefeningen.replace(oefeningNaam, new Oefening(naam, feedback, antwoord, opgave));

		}else {

			this.oefeningen.remove(oefeningNaam);
			controleerOpNaamMap(naam);
			this.oefeningen.put(naam, new Oefening(naam, feedback, antwoord, opgave));

		}

		Oefening oefening = this.oefeningen.get(oefeningNaam);
		oefening.setNaam(naam);
		oefening.setFeedback(feedback);
		// TODO - setAntwoord nog uitwerken
		oefening.setOpgave(opgave);
		this.oefeningen.remove(oefeningNaam);
		this.oefeningen.put(naam, oefening);*/
	}
	private boolean controleerOpNaamMap(String naam) {
		//return this.oefeningen.containsKey(naam);
        return true;
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