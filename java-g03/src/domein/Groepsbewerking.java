package domein;

public abstract class Groepsbewerking {

    private String naam;
    private String opgave;

    public Groepsbewerking(String naam, String opgave) {
        setNaam(naam);
        setOpgave(opgave);
    }

    public String getNaam() {
        return this.naam;
    }

    public void setNaam(String naam) {
        if (naam == null || naam.equals("")) {
            throw new IllegalArgumentException("Naam mag niet leeg zijn");
        }
        this.naam = naam;
    }

    public String getOpgave() {

        return this.opgave;
    }

    public void setOpgave(String opgave) {

        if (opgave == null || opgave.equals("")) {
            throw new IllegalArgumentException("Opgave mag niet leeg zijn");
        }
        this.opgave = opgave;
    }
}