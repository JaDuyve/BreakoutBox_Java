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
        if (naam == null || naam.equals("")){
            throw new IllegalArgumentException("Naam mag niet leeg zijn");
        }
		this.naam = naam;
	}

	public String getOpgave() {
		return this.opgave;
	}

	public void setOpgave(String opgave) {
        if (opgave == null || opgave.equals("")){
            throw new IllegalArgumentException("Opgave mag niet leeg zijn");
        }
		this.opgave = opgave;
	}

	public String getAntwoord() {

		return this.antwoord;
	}

	public void setAntwoord(String antwoord) {
        if (antwoord == null || antwoord.equals("")){
            throw new IllegalArgumentException("Antwoord mag niet leeg zijn");
        }
		this.antwoord = antwoord;
	}

	public String getFeedback() {
		return this.feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public void setGroepsbewerkingen(Collection<Groepsbewerking> groepsbewerkingen) {
		this.groepsbewerkingen = groepsbewerkingen;
	}

	public void setVak(Vak vak) {
		this.vak = vak;
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


		setNaam(naam);
		setOpgave(opgave);
		setAntwoord(antwoord);
		setFeedback(feedback);
		setVak(new Vak(vak));
		setGroepsbewerkingen(new ArrayList<Groepsbewerking>());
	}

	/**
	 *
	 * @param naam
	 * @param opgave
	 * @param antwoord
	 * @param vak
	 */
	public Oefening(String naam, String opgave, String antwoord, String vak) {
		this(naam, opgave, antwoord, null, vak);
	}

	/**
	 *
	 * @param groepsbewerking
	 */
	public void GroepsbewerkingToevoegen(Groepsbewerking groepsbewerking) {
		this.groepsbewerkingen.add(groepsbewerking);
	}

	/**
	 *
	 * @param groepsbewerking
	 */
	public void GroepsbewerkingVerwijderen(Groepsbewerking groepsbewerking) {
		this.groepsbewerkingen.remove(groepsbewerking);
	}

}