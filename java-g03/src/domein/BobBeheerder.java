package domein;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import persistentie.GenericDao;
import persistentie.GenericDaoJpa;

import java.util.Comparator;
import java.util.List;
import java.util.Observable;

public class BobBeheerder extends Observable {

    private Bob bob;
    private List<Bob> bobs;
    private FilteredList<Bob> filtBobs;
    private GenericDao<Bob> bobRepo;
    private GenericDao<Sessie> sessieRepo;

    private final Comparator<Bob> byBobNaam = (b1, b2) -> b1.getNaam().compareToIgnoreCase(b2.getNaam());


    public BobBeheerder(GenericDao mock) {
        this.bobRepo = mock;
        getBobList();
    }

    public BobBeheerder() {
        setBobRepo(new GenericDaoJpa<>(Bob.class));
        sessieRepo = new GenericDaoJpa<>(Sessie.class);
        getBobList();
    }

    public void setBobRepo(GenericDao<Bob> mock) {
        this.bobRepo = mock;
    }

    public ObservableList<Bob> geefBobs() {
        filtBobs = new FilteredList<>(FXCollections.observableList(getBobList()), e -> true);
        return new SortedList<>(filtBobs, byBobNaam);
    }

    private List<Bob> getBobList() {
        if (bobs == null) {
            bobs = bobRepo.findAll();
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
        filtBobs.setPredicate(bob -> {

            if (bobNaam == null || bobNaam.isEmpty()) {
                return true;
            }

            return bob.getNaam().toLowerCase().contains(bobNaam.toLowerCase());
        });

        if (filtBobs.size() == 0) {
            setBob(null);
        }
    }

    public void createBob(String naam, List<Oefening> oefeningen, List<Actie> acties) {
        Bob bob = new Bob(naam, oefeningen, acties);

        if (bobRepo.exists(bob.getNaam())) {
            throw new IllegalArgumentException("Breakout Box met naam: " + naam + " bestaat al");
        } else {
            GenericDaoJpa.startTransaction();
            bobRepo.insert(bob);
            GenericDaoJpa.commitTransaction();
            bobs.add(bob);
        }
    }

    public void verwijderBob() {
        controleerBobInSessie(bob);
        GenericDaoJpa.startTransaction();
        bobRepo.delete(bob);
        GenericDaoJpa.commitTransaction();
        bobs.remove(bob);
        bob = null;

    }

    public void wijzigBob(String naam, List<Oefening> oefeningen, List<Actie> acties) {
        controleerBobInSessie(bob);
        if (!bob.getNaam().equals(naam)) {
            createBob(naam, oefeningen, acties);
            GenericDaoJpa.startTransaction();
            bobRepo.delete(bob);
            GenericDaoJpa.commitTransaction();
            bobs.remove(bob);
        } else {
            GenericDaoJpa.startTransaction();

            if (!oefeningen.containsAll(bob.getLijstOefeningen())) {
                bob.setLijstOefeningen(oefeningen);
            }
            if (!acties.containsAll(bob.getLijstActies())) {
                bob.setLijstActies(acties);
            }
            GenericDaoJpa.commitTransaction();
            setBob(bob);
        }


    }

    private void controleerBobInSessie(Bob bob) {
        boolean result = false;
        for (Sessie sessie : sessieRepo.findAll()){
            result = sessie.getBob().getNaam().equalsIgnoreCase(bob.getNaam());
            if (result){
                break;
            }
        }


        if (result) {
            throw new IllegalArgumentException("Breakout Box is nog gelinkte met een Sessie, hierdoor is het niet mogelijk om deze Breakout Box te verwijderen.");
        }
    }


}
