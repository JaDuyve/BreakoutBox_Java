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


	public NumerischeOefening(String naam, String opgave, String feedback, String vak, double antwoord) {
		// TODO - implement NumerischeOefening.NumerischeOefening
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param naam
	 * @param opgave
	 * @param vak
	 * @param antwoord
	 */
	public NumerischeOefening(String naam, String opgave, String vak, double antwoord) {
		// TODO - implement NumerischeOefening.NumerischeOefening
		throw new UnsupportedOperationException();
	}

}