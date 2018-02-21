package domein;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.Objects;

@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class Groepsbewerking {

    @Id
    private String naam;
    private String opgave;


    protected  Groepsbewerking()
    {

    }

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