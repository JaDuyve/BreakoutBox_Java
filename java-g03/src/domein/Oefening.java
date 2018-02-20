package domein;

import java.util.*;

public class Oefening {

	private Vak vak;
	private Collection<Groepsbewerking> groepsbewerkingen;
	private String naam;
	private String opgave;
	private String antwoord;
	private String feedback;

	public String getNaam() { return this.naam;	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getOpgave() {
		return this.opgave;
	}

	public void setOpgave(String opgave) {
		this.opgave = opgave;
	}

	public String getAntwoord() {
		return this.antwoord;
	}

	public void setAntwoord(String antwoord) {
		this.antwoord = antwoord;
	}

	public String getFeedback() {
		return this.feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	/**
	 *
	 * @param naam
	 * @param opgave
	 * @param antwoord
	 * @param feedback
	 * @param vak
	 */
	public Oefening(String naam, String opgave, String antwoord, String feedback, String vak) {
		// TODO - implement domein.Oefening.domein.Oefening
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param naam
	 * @param opgave
	 * @param antwoord
	 * @param vak
	 */
	public Oefening(String naam, String opgave, String antwoord, String vak) {
		// TODO - implement domein.Oefening.domein.Oefening
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param groepsbewerking
	 */
	public void GroepsbewerkingToevoegen(Groepsbewerking groepsbewerking) {
		// TODO - implement domein.Oefening.GroepsbewerkingToevoegen
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param groepsbewerking
	 */
	public void GroepsbewerkingVerwijderen(Groepsbewerking groepsbewerking) {
		// TODO - implement domein.Oefening.GroepsbewerkingVerwijderen
		throw new UnsupportedOperationException();
	}

}