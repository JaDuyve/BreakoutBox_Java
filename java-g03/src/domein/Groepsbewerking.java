package domein;

import javax.persistence.*;
import java.util.*;

@Entity
public class Groepsbewerking {

	@Id
	private String naam;
	private String opgave;
	private String waarde;

	@Enumerated(EnumType.STRING)
	private Bewerking bewerking;


	public Groepsbewerking(){};

	/**
	 *
	 * @param naam
	 * @param opgave
	 */
	public Groepsbewerking(String naam, String opgave, String waarde, Bewerking bewerking) {
		setNaam(naam);
		setOpgave(opgave);
		setWaarde(waarde);
		setBewerking(bewerking);
	}

	public String getNaam() {
		return this.naam;
	}

	public void setNaam(String naam) {
		if (naam == null || naam.equals("")){
			throw new IllegalArgumentException("Naam van groepsBewerking niet leeg laten.");
		}
		this.naam = naam;
	}

	public String getOpgave() {
		return this.opgave;
	}

	public void setOpgave(String opgave) {
		this.opgave = opgave;
	}

	public String getWaarde() {
		return waarde;
	}

	public void setWaarde(String waarde) {
		this.waarde = waarde;
	}

	public Bewerking getBewerking() {
		return bewerking;
	}

	public void setBewerking(Bewerking bewerking) {
		this.bewerking = bewerking;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Groepsbewerking)) return false;
		Groepsbewerking that = (Groepsbewerking) o;
		return Objects.equals(getNaam(), that.getNaam());
	}

	@Override
	public int hashCode() {

		return Objects.hash(getNaam());
	}

	@Override
	public String toString() {
		return this.naam;
	}
}