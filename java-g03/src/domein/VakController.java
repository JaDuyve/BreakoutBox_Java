package domein;

import gui.BobDetailPaneelController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistentie.GenericDao;
import persistentie.GenericDaoJpa;

import java.util.List;

public class VakController {

    private VakBeheerder vakBeheerder = new VakBeheerder();



    public Vak geefVak(){

        return vakBeheerder.getVak();
    }

    public ObservableList<Vak> geefVakken(){

        return vakBeheerder.geefVakken();
    }

    public void changeFilter(String naam){

        vakBeheerder.changeFilter(naam);
    }

    public void createBob(String naam, String color){
        vakBeheerder.createVak(naam, color);
    }

    public void verwijderBob(){

        vakBeheerder.verwijderVak();
    }

    public void wijzigBob(String naam, String color){
        vakBeheerder.wijzigeVak(naam, color);
    }

    public void veranderHuidige(Vak vak) {
        vakBeheerder.setVak(vak);
    }


}
