package domein;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity

public class NumerischeGroepsbewerking extends Groepsbewerking {


	protected NumerischeGroepsbewerking(){};
	/**
	 *
	 * @param naam
	 * @param opgave
	 * @param waarde
	 * @param bewerking
	 */
	public NumerischeGroepsbewerking(String naam, String opgave, double waarde, Bewerking bewerking) {
		// TODO - implement NumerischeGroepsbewerking.NumerischeGroepsbewerking
		throw new UnsupportedOperationException();
	}

	private Bewerking bewerking;
	private double waarde;

}