package domein;

import gui.SessieDetailPaneelController;
import javafx.collections.ObservableList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.util.Date;

public class SessieController {
    private SessieBeheerder sessieBeheerder;

    public SessieController() {
    }

    public void create(String naam, Date startDate, Bob bob, File groepen, boolean contactLeer){
        sessieBeheerder.create(naam, startDate, bob, groepen, contactLeer);
    }

    public void addObserver(SessieDetailPaneelController sessieDetailPaneelController){
        throw new NotImplementedException();
    }

    public ObservableList<Bob> geefBobs(){
        throw new NotImplementedException();
    }

    public void changeFilter(String sessieNaam){
        sessieBeheerder.changeFilter(sessieNaam);
    }
}
