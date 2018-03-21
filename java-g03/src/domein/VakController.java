package domein;

import gui.BobDetailPaneelController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistentie.GenericDao;
import persistentie.GenericDaoJpa;

import java.util.List;
import java.util.Observer;

public class VakController {

    private VakBeheerder vakBeheerder = new VakBeheerder();

    public VakController() {
        this.vakBeheerder = vakBeheerder;
    }

    public Vak geefVak(){

        return vakBeheerder.getVak();
    }

    public ObservableList<Vak> geefVakken(){

        return vakBeheerder.geefVakken();
    }

    public void changeFilter(String naam){

        vakBeheerder.changeFilter(naam);
    }

    public void createVak(String naam){
        vakBeheerder.createVak(naam);
    }

    public void verwijderVak(){

        vakBeheerder.verwijderVak();
    }

    public void wijzigVak(String naam){
        vakBeheerder.wijzigeVak(naam);
    }

    public void veranderHuidige(Vak vak) {
        vakBeheerder.setVak(vak);
    }

    public void addObserver(Observer observer){
        vakBeheerder.addObserver(observer);
    }


}
