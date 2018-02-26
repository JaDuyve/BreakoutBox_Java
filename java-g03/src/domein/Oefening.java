package domein;

import java.util.*;

public abstract class Oefening {

	private Collection<Groepsbewerking> lijstGroepsbewerkingen;
	private Vak vak;
	private String naam;
	private String opgave;
	private String feedback;

	public String getNaam() {
		return this.naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getOpgave() {
		return this.opgave;
	}

	public void setOpgave(String opgave) {
		this.opgave = opgave;
	}

	public String getFeedback() {
		return this.feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}


	public Oefening(){};
	/**
	 *
	 * @param naam
	 * @param opgave
	 * @param feedback
	 * @param vak
	 */
	public Oefening(String naam, String opgave, String feedback, String vak) {
		// TODO - implement Oefening.Oefening
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param naam
	 * @param opgave
	 * @param vak
	 */
	public Oefening(String naam, String opgave, String vak) {
		// TODO - implement Oefening.Oefening
		throw new UnsupportedOperationException();
	}

}