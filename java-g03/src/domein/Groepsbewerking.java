package domein;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Groepsbewerking {

	@Id
	private String naam;
	private String opgave;

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

	public Groepsbewerking(){};

	/**
	 *
	 * @param naam
	 * @param opgave
	 */
	public Groepsbewerking(String naam, String opgave) {
		// TODO - implement Groepsbewerking.Groepsbewerking
		throw new UnsupportedOperationException();
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
}