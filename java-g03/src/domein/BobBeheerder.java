package domein;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import persistentie.GenericDao;
import persistentie.GenericDaoJpa;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Comparator;
import java.util.List;
import java.util.Observable;

public class BobBeheerder extends Observable {

    private Bob bob;
    private FilteredList<Bob> bobs;
    private GenericDao<Bob> bobRepo;

    private final Comparator<Bob> byBobNaam = (b1, b2) -> b1.getNaam().compareToIgnoreCase(b2.getNaam());

    public BobBeheerder() {
        setBobRepo(new GenericDaoJpa<>(Bob.class));
    }

    public void setBobRepo(GenericDao<Bob> mock) {
        this.bobRepo = mock;
    }

    public Bob geefBob(String naam) {
        return bobRepo.get(naam);
    }

    public ObservableList<Bob> geefBobs() {
        return new SortedList<>(getBobList(), byBobNaam);
    }

    private ObservableList<Bob> getBobList() {
        if (bobs == null) {
            bobs = new FilteredList<>(FXCollections.observableList(bobRepo.findAll()), e -> true);
        }

        return bobs;
    }

    public void setBob(Bob bob) {
        this.bob = bob;
        setChanged();
        notifyObservers(bob);
    }

    public void changeFilter(String bobNaam) {
        bobs.setPredicate(oefening -> {

            boolean bobNaamLeeg = bobNaam == null || bobNaam.isEmpty();

            String lowercaseBobNaam = "";

            if (!bobNaamLeeg) {
                lowercaseBobNaam = bobNaam.toLowerCase();
            }

            boolean conditieBobNaam = bobNaamLeeg ? false : oefening.getNaam().toLowerCase().contains(lowercaseBobNaam);


            return conditieBobNaam ;
        });
    }

    public void createBob(String naam, List<Oefening> oefeningen, List<Actie> acties, List<Toegangscode> toegangscodes) {

        Bob bob = new Bob(naam, oefeningen, acties, toegangscodes);

        if (bobRepo.exists(bob.getNaam())){
            throw new IllegalArgumentException("Breakout Box met naam: " + naam + " bestaat al");
        }else {
            GenericDaoJpa.startTransaction();
            bobRepo.insert(bob);
            GenericDaoJpa.commitTransaction();
            bobs = null;
            getBobList();
        }
    }

    public void verwijderBob() {
            if (bob.getLijstOefeningen().isEmpty() && bob.getLijstActies().isEmpty() && bob.getLijstToegangscode().isEmpty()) {
                GenericDaoJpa.startTransaction();
                bobRepo.delete(bob);
                GenericDaoJpa.commitTransaction();
                bobs = null;
                getBobList();
        }
        else {
            throw new IllegalArgumentException("Uw bob moet leeg zijn.");
        }
    }

    public void wijzigBob(String bobNaam, String naam) {
        throw new NotImplementedException();
    }


}
