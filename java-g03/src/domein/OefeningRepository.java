package domein;

import persistentie.OefeningMapper;

import java.util.*;

public class OefeningRepository {

	private Collection<Oefening> oefeningen;

	private OefeningMapper oefeningMapper;



	public OefeningRepository() {
		// TODO - implement OefeningRepository.OefeningRepository
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