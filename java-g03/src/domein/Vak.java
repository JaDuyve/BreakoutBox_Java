package domein;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@NamedQueries({
		@NamedQuery(name="Vak.findAll",query="SELECT v FROM Vak v")
})
@Entity
public class Vak implements Serializable{

	@Id
	private String naam;
	private String color;

	public Vak(String naam, String color) {
		this.naam = naam;
		this.color = color;
	}

	protected Vak()
	{

	}



	public String getNaam() {

		return this.naam;
	}

	public void setNaam(String naam) {

		this.naam = naam;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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