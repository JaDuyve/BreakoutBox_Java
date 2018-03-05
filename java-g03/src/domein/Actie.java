package domein;

import javax.persistence.Entity;
import javax.persistence.Id;

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
}
