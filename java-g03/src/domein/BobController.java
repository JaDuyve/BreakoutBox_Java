package domein;

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

    public Bob geefBob(String naam){

        return bobBeheerder.geefBob(naam);
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

    public void changeFilter(String bobNaam, List<String> vakken){

        bobBeheerder.changeFilter(bobNaam, vakken);
    }

    public void createBob(String naam, List<Oefening> oefeningen, List<Actie> acties, List<Toegangscode> toegangscodes, Vak bobVak){
        bobBeheerder.createBob(naam, oefeningen,acties,toegangscodes,bobVak);
    }

    public ObservableList<Toegangscode> geefToegangsCodes(){
        return FXCollections.observableArrayList(toegangscodeRepo.findAll());
    }

    public void verwijderBob(String naam){

        bobBeheerder.verwijderBob(naam);
    }

    public void wijzigBob(String bobNaam, String naam, Vak vak){

        bobBeheerder.wijzigBob(bobNaam, naam, vak);
    }
}
