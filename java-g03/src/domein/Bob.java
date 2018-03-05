package domein;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class Bob {
    private String naam;
    private List<Oefening> lijstOefeningen;
    private List<Actie> lijstActies;
    private List<Toegangscode> lijstToegangscode;
    private Vak vak;

    public Bob(String naam, Vak vak) {
        this.naam = naam;
        this.vak = vak;
    }

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
