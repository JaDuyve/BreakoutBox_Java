package domein;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Bob {

    @Id
    private String naam;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Oefening> lijstOefeningen;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Actie> lijstActies;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Toegangscode> lijstToegangscode;


    public Bob(String naam, List<Oefening> oefeningen, List<Actie> acties, List<Toegangscode> toegangscodes) {
        setNaam(naam);
        setLijstOefeningen(oefeningen);
        setLijstActies(acties);
        setLijstToegangscode(toegangscodes);
    }

    protected Bob(){

    }

    public String getNaam() {

        return naam;
    }

    public void setNaam(String naam) {
        if (naam == null || naam.isEmpty()){
            throw new IllegalArgumentException("Naam Breakout Box mag niet leeg zijn");
        }
        this.naam = naam;
    }

    public List<Oefening> getLijstOefeningen() {
        return lijstOefeningen;
    }

    public void setLijstOefeningen(List<Oefening> lijstOefeningen) {
        this.lijstOefeningen = lijstOefeningen;
    }

    public List<Actie> getLijstActies() {
        return lijstActies;
    }

    public void setLijstActies(List<Actie> lijstActies) {
        this.lijstActies = lijstActies;
    }

    public List<Toegangscode> getLijstToegangscode() {
        return lijstToegangscode;
    }

    public void setLijstToegangscode(List<Toegangscode> lijstToegangscode) {
        this.lijstToegangscode = lijstToegangscode;
    }


    @Override
    public String toString() {
        return naam;
    }
}
