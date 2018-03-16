package domein;

import javax.persistence.*;
import java.util.List;

@Entity
public class Groep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    private String klas;

    @ElementCollection
    private List<String> leerlingen;
    @ElementCollection
    private List<Integer> oefVolg;

    public Groep(String naam, String klas, List<String> leerlingen) {
        setNaam(naam);
        setKlas(klas);
        setLeerlingen(leerlingen);
        setOefVolg(oefVolg);
    }

    protected Groep() {
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        if (naam.isEmpty() || naam.equals("")){
            throw new IllegalArgumentException("Naam mag niet leeg zijn");
        }
        this.naam = naam;
    }

    public String getKlas() {
        return klas;
    }

    public void setKlas(String klas) {
        if (klas.isEmpty() || klas.equals("")){
            throw new IllegalArgumentException("Klas mag niet leeg zijn");
        }
        this.klas = klas;
    }

    public List<String> getLeerlingen() {
        return leerlingen;
    }

    public void setLeerlingen(List<String> leerlingen) {
        if (leerlingen.isEmpty()){
            throw new IllegalArgumentException("Leerlingen mag niet leeg zijn");
        }
        this.leerlingen = leerlingen;
    }

    public List<?> getOefVolg() {
        return oefVolg;
    }

    public void setOefVolg(List<Integer> oefVolg) {
        if (oefVolg.isEmpty()){
            throw new IllegalArgumentException(("oefVolg mag niet leeg zijn"));
        }
        this.oefVolg = oefVolg;
    }
}
