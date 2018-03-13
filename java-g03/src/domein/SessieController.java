package domein;

import gui.SessieDetailPaneelController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistentie.GenericDao;
import persistentie.GenericDaoJpa;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.time.LocalDate;
import java.util.Date;

public class SessieController {
    private SessieBeheerder sessieBeheerder;
    private GenericDao<Bob> bobRepo;

    public SessieController() {
        bobRepo = new GenericDaoJpa<>(Bob.class);
        sessieBeheerder = new SessieBeheerder();
    }

    public void create(String naam, LocalDate startDate, Bob bob, File groepen, boolean contactLeer){
        sessieBeheerder.create(naam, startDate, bob, groepen, contactLeer);
    }

    public void addObserver(SessieDetailPaneelController sessieDetailPaneelController){
        throw new NotImplementedException();
    }

    public ObservableList<Bob> geefBobs(){
        return FXCollections.observableList(bobRepo.findAll());
    }

    public void changeFilter(String sessieNaam){
        sessieBeheerder.changeFilter(sessieNaam);
    }
}
