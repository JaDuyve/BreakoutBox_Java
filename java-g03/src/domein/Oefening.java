package domein;

import javax.persistence.*;
import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Oefening {


	@Id
	private String naam;
	private String opgave;
	private String feedback;
	@OneToMany
	private Collection<Groepsbewerking> lijstGroepsbewerkingen;
	@ManyToOne
	private Vak vak;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Oefening)) return false;
		Oefening oefening = (Oefening) o;
		return Objects.equals(getNaam(), oefening.getNaam());
	}

	@Override
	public int hashCode() {

		return Objects.hash(getNaam());
	}
}