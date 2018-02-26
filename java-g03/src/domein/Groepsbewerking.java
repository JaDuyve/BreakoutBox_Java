package domein;

public abstract class Groepsbewerking {

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

}