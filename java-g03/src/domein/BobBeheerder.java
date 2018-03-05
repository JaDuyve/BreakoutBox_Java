package domein;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class BobBeheerder {

    private Bob bob;
    private FilteredList<Bob> bobs;

    public BobBeheerder(){
    };

    public Bob geefBob(String naam){
        throw new NotImplementedException();
    }

    public ObservableList<Bob> geefBobs(){
        throw new NotImplementedException();
    }

    public Bob filterOpNaam(String bobNaam){
        throw new NotImplementedException();
    }

    public void createBob(String naam, Vak bobVak){
        throw new NotImplementedException();
    }

    public void verwijderBob(String naam){
        throw new NotImplementedException();
    }

    public void wijzigBob(String bobNaam, String naam, Vak vak){
        throw new NotImplementedException();
    }




}
