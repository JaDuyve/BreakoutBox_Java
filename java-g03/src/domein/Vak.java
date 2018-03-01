package domein;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Vak {

    @Id
    private String naam;
    private String kleur;

    public String getNaam() {
        return this.naam;
    }

    public void setNaam(String naam) {

        this.naam = naam;
    }

    public String getKleur() {
        return kleur;
    }

    public void setKleur(String kleur) {
        this.kleur = kleur;
    }

    protected Vak() {
    }

    public Vak(String naam, String kleur) {
        setNaam(naam);
        setKleur(kleur);
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