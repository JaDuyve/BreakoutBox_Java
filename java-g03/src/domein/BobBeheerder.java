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

public class BobBeheerder {

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

    public void changeFilter(String bobNaam, List<String> vakken) {
        bobs.setPredicate(oefening -> {

            boolean bobNaamLeeg = bobNaam == null || bobNaam.isEmpty();
            boolean vakkenLeeg = vakken == null || vakken.isEmpty();

            if (bobNaamLeeg && vakkenLeeg) {
                return true;
            }

            String lowercaseBobNaam = "";

            if (!bobNaamLeeg) {
                lowercaseBobNaam = bobNaam.toLowerCase();
            }

            boolean conditieOefeningNaam = bobNaamLeeg ? false : oefening.getNaam().toLowerCase().contains(lowercaseBobNaam);
            boolean conditieVakken = vakkenLeeg ? false : vakken.stream().anyMatch(t -> t.equals(bob.getVak().getNaam()));

            if (bobNaamLeeg) {
                return conditieVakken;
            } else if (vakkenLeeg) {
                return conditieOefeningNaam;
            }

            return conditieOefeningNaam && conditieVakken;
        });
    }

    public void createBob(String naam, List<Oefening> oefeningen, List<Actie> acties, List<Toegangscode> toegangscodes, Vak bobVak) {

        Bob bob = new Bob(naam, oefeningen, acties, toegangscodes, bobVak);

        if (bobRepo.exists(bob.getNaam())){
            throw new IllegalArgumentException("Breakout Box met naam: " + naam + " bestaat al");
        }else {
            GenericDaoJpa.startTransaction();
            bobRepo.insert(bob);
            GenericDaoJpa.commitTransaction();
        }
    }

    public void verwijderBob(String naam) {
        Bob bob = geefBob(naam);
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

    public void wijzigBob(String bobNaam, String naam, Vak vak) {
        throw new NotImplementedException();
    }


}
