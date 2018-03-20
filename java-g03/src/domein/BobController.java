package domein;

import gui.BobDetailPaneelController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistentie.GenericDao;
import persistentie.GenericDaoJpa;

import java.util.List;
import java.util.Observer;

public class BobController {

    private GenericDao<Actie> actieRepo;
    private GenericDao<Oefening> oefeningRepo;

    private BobBeheerder bobBeheerder;

    public BobController(){
        actieRepo = new GenericDaoJpa<>(Actie.class);
        oefeningRepo = new GenericDaoJpa<>(Oefening.class);
        bobBeheerder = new BobBeheerder();

    }

    public Bob geefBob(){

        return bobBeheerder.getBob();
    }

    public ObservableList<Bob> geefBobs(){

        return bobBeheerder.geefBobs();
    }

    public ObservableList<Oefening> geefOefeningen(){
        return FXCollections.observableArrayList(oefeningRepo.findAll());
    }

    public ObservableList<Actie> geefActies(){
        return FXCollections.observableArrayList(actieRepo.findAll());
    }

    public void changeFilter(String bobNaam){

        bobBeheerder.changeFilter(bobNaam);
    }

    public void createBob(String naam, List<Oefening> oefeningen, List<Actie> acties){
        bobBeheerder.createBob(naam, oefeningen,acties);
    }

    public void verwijderBob(){

        bobBeheerder.verwijderBob();
    }

    public void wijzigBob(String naam, List<Oefening> oefeningen, List<Actie> acties){
        bobBeheerder.wijzigBob(naam, oefeningen, acties);
    }

    public void veranderHuidigeBob(Bob bob) {
        bobBeheerder.setBob(bob);
    }

    public void addObserver(Observer observer){
        bobBeheerder.addObserver(observer);
    }
}
