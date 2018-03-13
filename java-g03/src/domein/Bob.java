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

        controleerGenoegActies();
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
        if (lijstActies == null || lijstActies.isEmpty()) {
            throw new IllegalArgumentException("Acties mag niet leeg gelaten worden.");
        }
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

    public void controleerGenoegActies(){
        if (lijstOefeningen.size() != lijstActies.size()){
            throw new IllegalArgumentException("Er moeten evenveel oefeningen zijn als acties.");
        }
    }


    @Override
    public String toString() {
        return naam;
    }
}
