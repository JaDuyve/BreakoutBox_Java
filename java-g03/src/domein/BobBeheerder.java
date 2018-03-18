package domein;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import persistentie.GenericDao;
import persistentie.GenericDaoJpa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;

public class BobBeheerder extends Observable {

    private Bob bob;
    private FilteredList<Bob> bobs;
    private GenericDao<Bob> bobRepo;

    private final Comparator<Bob> byBobNaam = (b1, b2) -> b1.getNaam().compareToIgnoreCase(b2.getNaam());


        public BobBeheerder(GenericDao mock)
        {
            this.bobRepo = mock;
            getBobList();
        }
    public BobBeheerder() {
        setBobRepo(new GenericDaoJpa<>(Bob.class));
        getBobList();
    }

    public void setBobRepo(GenericDao<Bob> mock) {
        this.bobRepo = mock;
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

    public Bob getBob() {
        return bob;
    }

    public void setBob(Bob bob) {
        this.bob = bob;
        setChanged();
        notifyObservers(bob);
    }

    public void changeFilter(String bobNaam) {
        bobs.setPredicate(oefening -> {

            if (bobNaam == null || bobNaam.isEmpty()) {
                return true;
            }

            return oefening.getNaam().toLowerCase().contains(bobNaam.toLowerCase());
        });
    }

    public void createBob(String naam, List<Oefening> oefeningen, List<Actie> acties) {
        List<Toegangscode> toegangscodes = new ArrayList<>(); // TODO - toegangscodes laten genereren best in bob zelf
        Bob bob = new Bob(naam, oefeningen, acties, toegangscodes);

        if (bobRepo.exists(bob.getNaam())) {
            throw new IllegalArgumentException("Breakout Box met naam: " + naam + " bestaat al");
        } else {
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
        } else {
            throw new IllegalArgumentException("Uw bob moet leeg zijn.");
        }
    }

    public void wijzigBob(String naam, List<Oefening> oefeningen, List<Actie> acties) {
        GenericDaoJpa.startTransaction();
        bobRepo.delete(bob);
        GenericDaoJpa.commitTransaction();
        createBob(naam, oefeningen, acties);

    }


}
