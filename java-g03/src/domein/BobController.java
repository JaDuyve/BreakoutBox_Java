package domein;

import gui.BobDetailPaneelController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistentie.GenericDao;
import persistentie.GenericDaoJpa;

import java.util.List;

public class BobController {

    private GenericDao<Actie> actieRepo;
    private GenericDao<Oefening> oefeningRepo;
    private GenericDao<Toegangscode> toegangscodeRepo;

    private BobBeheerder bobBeheerder = new BobBeheerder();

    public BobController(){
        actieRepo = new GenericDaoJpa<>(Actie.class);
        oefeningRepo = new GenericDaoJpa<>(Oefening.class);
        toegangscodeRepo = new GenericDaoJpa<>(Toegangscode.class);
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

    public void createBob(String naam, List<Oefening> oefeningen, List<Actie> acties, List<Toegangscode> toegangscodes){
        bobBeheerder.createBob(naam, oefeningen,acties,toegangscodes);
    }

    public ObservableList<Toegangscode> geefToegangsCodes(){
        return FXCollections.observableArrayList(toegangscodeRepo.findAll());
    }

    public void verwijderBob(){

        bobBeheerder.verwijderBob();
    }

    public void wijzigBob(String naam, List<Oefening> oefeningen, List<Actie> acties, List<Toegangscode> toegangscodes){
        bobBeheerder.wijzigBob(naam, oefeningen, acties, toegangscodes);
    }

    public void veranderHuidigeBob(Bob bob) {
        bobBeheerder.setBob(bob);
    }


    public void addObserver(BobDetailPaneelController bobDetailPaneelController){
        bobBeheerder.addObserver(bobDetailPaneelController);
    }
}
