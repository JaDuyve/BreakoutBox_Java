package domein;

import javafx.collections.ObservableList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.NotActiveException;

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

    public Bob filterOpNaam(String bobNaam){
        return bobBeheerder.filterOpNaam(bobNaam);
    }

    public void CreateBob(String naam, Vak bobVak){
        bobBeheerder.createBob(naam, bobVak);
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
