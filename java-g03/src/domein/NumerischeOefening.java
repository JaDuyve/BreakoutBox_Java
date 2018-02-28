package domein;

import javax.persistence.Entity;
import java.util.List;

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


	public NumerischeOefening(String naam, String opgave, String feedback, List<Groepsbewerking> lijstGroepsbewerkingen, Vak vak, double antwoord) {
		super(naam, opgave, feedback, lijstGroepsbewerkingen, vak);
		setAntwoord(antwoord);
	}

	/**
	 *
	 * @param naam
	 * @param opgave
	 * @param vak
	 * @param antwoord
	 */
	public NumerischeOefening(String naam, String opgave, List<Groepsbewerking> lijstGroepsbewerkingen, Vak vak, double antwoord) {
		super(naam, opgave, lijstGroepsbewerkingen, vak);
		setAntwoord(antwoord);
	}


	public double getAntwoord() {
		return antwoord;
	}

	public void setAntwoord(double antwoord) {
		this.antwoord = antwoord;
	}
}