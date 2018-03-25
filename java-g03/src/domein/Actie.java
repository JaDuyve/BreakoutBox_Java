package domein;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Actie {
    @Id
    private String naam;
    private String opgave;

    public Actie(String naam, String opgave) {
        setNaam(naam);
        setOpgave(opgave);
    }

    protected Actie(){

    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getOpgave() {
        return opgave;
    }

    public void setOpgave(String opgave) {
        this.opgave = opgave;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actie actie = (Actie) o;
        return Objects.equals(naam, actie.naam);
    }

    @Override
    public int hashCode() {

        return Objects.hash(naam);
    }

    @Override
    public String toString() {
        return  naam.toLowerCase();
    }
}
