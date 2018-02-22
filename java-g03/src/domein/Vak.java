package domein;

import javax.persistence.*;
import java.util.Objects;

@NamedQueries({
		@NamedQuery(name="Vak.findAll",query="SELECT v FROM Vak v")
})
@Entity
public class Vak {

	@Id
	private String naam;

	protected Vak()
	{

	}

	public String getNaam() {
		return this.naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}


	public Vak(String naam) {
		this.naam = naam;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Vak)) return false;
		Vak vak = (Vak) o;
		return Objects.equals(getNaam(), vak.getNaam());
	}

	@Override
	public int hashCode() {

		return Objects.hash(getNaam());
	}
}