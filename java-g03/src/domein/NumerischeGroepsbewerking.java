package domein;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity

public class NumerischeGroepsbewerking extends Groepsbewerking {

	private Bewerking bewerking;
	private double waarde;


	protected NumerischeGroepsbewerking(){};
	/**
	 *
	 * @param naam
	 * @param opgave
	 * @param waarde
	 * @param bewerking
	 */
	public NumerischeGroepsbewerking(String naam, String opgave, double waarde, Bewerking bewerking) {
		super(naam, opgave);
		setBewerking(bewerking);
		setWaarde(waarde);

	}

	public Bewerking getBewerking() {
		return bewerking;
	}

	public void setBewerking(Bewerking bewerking) {
		this.bewerking = bewerking;
	}

	public double getWaarde() {
		return waarde;
	}

	public void setWaarde(double waarde) {
		this.waarde = waarde;
	}



}