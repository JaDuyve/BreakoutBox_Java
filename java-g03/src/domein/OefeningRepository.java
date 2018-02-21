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

    public HashMap<String, Oefening> geefOefeningen(){
	    throw new UnsupportedOperationException();
    }

    /**
     *
     * @param oefening
     */
    public void voegOefeningToe(Oefening oefening) {
        // TODO - implement OefeningRepository.voegOefeningToe
        throw new UnsupportedOperationException();
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
		// TODO - implement OefeningRepository.wijzigOefening
		throw new UnsupportedOperationException();
	}

    /**
     *
     * @param oefening
     */
    public void VerwijderOefening(Oefening oefening) {
        // TODO - implement OefeningRepository.VerwijderOefening
        throw new UnsupportedOperationException();
    }

    /*
     * Deze constructor word door de testen gebruikt om de dummy te instantiëren
     */
	public OefeningRepository(OefeningMapper oefeningMapper) {
		this.oefeningMapper = oefeningMapper;
	}
}