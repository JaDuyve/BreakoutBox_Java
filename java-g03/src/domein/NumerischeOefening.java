package domein;

import javax.persistence.Entity;

@Entity
public class NumerischeOefening extends Oefening {

	private double antwoord;


	protected NumerischeOefening(){};
	/**
	 *
	 * @param naam
	 * @param opgave
	 * @param feedback
	 * @param vak
	 * @param antwoord
	 */


	public NumerischeOefening(String naam, String opgave, String feedback, Vak vak, double antwoord) {
		super(naam, opgave, feedback, vak);
		setAntwoord(antwoord);
	}

	/**
	 *
	 * @param naam
	 * @param opgave
	 * @param vak
	 * @param antwoord
	 */
	public NumerischeOefening(String naam, String opgave, Vak vak, double antwoord) {
		super(naam, opgave, vak);
		setAntwoord(antwoord);
	}


	public double getAntwoord() {
		return antwoord;
	}

	public void setAntwoord(double antwoord) {
		this.antwoord = antwoord;
	}
}