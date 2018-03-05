package domein;

import javafx.collections.ObservableList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class BobController {

    private BobBeheerder bobBeheerder = new BobBeheerder();

    public BobController(){

    }

    public Bob geefBob(String naam){
        return bobBeheerder.geefBob(naam);
    }

    public ObservableList<Bob> geefBobs(){
        return bobBeheerder.geefBobs();
    }

    public ObservableList<Actie> geefActies(){
        throw new NotImplementedException();
    }

    public void changeFilter(String bobNaam, List<String> vakken){
        bobBeheerder.changeFilter(bobNaam, vakken);
    }

    public void CreateBob(String naam, List<Oefening> oefeningen, List<Actie> acties, List<Toegangscode> toegangscodes, Vak bobVak){
        bobBeheerder.createBob(naam, oefeningen,acties,toegangscodes,bobVak);
    }

    public ObservableList<Toegangscode> geefToegangsCodes(){
        throw new NotImplementedException();
    }

    public void verwijderBob(String naam){
        bobBeheerder.verwijderBob(naam);
    }

    public void wijzigBob(String bobNaam, String naam, Vak vak){
        bobBeheerder.wijzigBob(bobNaam, naam, vak);
    }
}
