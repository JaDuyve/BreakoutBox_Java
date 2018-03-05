package domein;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class Bob {
    private String naam;
    private List<Oefening> lijstOefeningen;
    private List<Actie> lijstActies;
    private List<Toegangscode> lijstToegangscode;
    private Vak vak;

    public Bob(String naam, List<Oefening> oefeningen, List<Actie> acties, List<Toegangscode> toegangscodes, Vak vak) {
        setNaam(naam);
        setLijstOefeningen(oefeningen);
        setLijstActies(acties);
        setLijstToegangscode(toegangscodes);
        setVak(vak);
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

    public Vak getVak() {
        return vak;
    }

    public void setVak(Vak vak) {
        this.vak = vak;
    }

    //mss weg doen??????
    public void voegToegangsCodeToe(Toegangscode code){
        throw new NotImplementedException();
    }

    public void verwijderToegangscode(Toegangscode code){
        throw new NotImplementedException();
    }

    public void voegActieToe(Actie actie){
        throw new NotImplementedException();
    }

    public void verwijderActie(Actie actie){
        throw new NotImplementedException();
    }

    public void voegOefeningToe(Oefening oefening){
        throw new NotImplementedException();
    }

    public void verwijderOefening(Oefening oefening){
        throw new NotImplementedException();
    }
}
