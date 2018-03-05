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

    public BobBeheerder(){
        setBobRepo(new GenericDaoJpa<>(Bob.class));
    }

    public void setBobRepo(GenericDao<Bob> mock){
        this.bobRepo = mock;
    }

    public Bob geefBob(String naam){
        throw new NotImplementedException();
    }

    public ObservableList<Bob> geefBobs(){
        return new SortedList<>(getBobList(), byBobNaam);
    }

    private ObservableList<Bob> getBobList(){
        if (bobs == null){
            bobs = new FilteredList<>(FXCollections.observableList(bobRepo.findAll()), e -> true);
        }

        return bobs;
    }

    public void  changeFilter(String bobNaam, List<String> vakken){
        bobs.setPredicate(oefening -> {

            boolean bobNaamLeeg = bobNaam == null || bobNaam.isEmpty();
            boolean vakkenLeeg = vakken == null || vakken.isEmpty();

            if (bobNaamLeeg && vakkenLeeg){
                return true;
            }

            String lowercaseBobNaam = "";

            if (!bobNaamLeeg){
                lowercaseBobNaam = bobNaam.toLowerCase();
            }

            boolean conditieOefeningNaam = bobNaamLeeg ? false: oefening.getNaam().toLowerCase().contains(lowercaseBobNaam);
            boolean conditieVakken = vakkenLeeg ? false: vakken.stream().anyMatch(t -> t.equals(bob.getVak().getNaam()));

            if (bobNaamLeeg){
                return conditieVakken;
            }else if (vakkenLeeg){
                return conditieOefeningNaam;
            }

            return conditieOefeningNaam && conditieVakken;
        });
    }

    public void createBob(String naam, Vak bobVak){

        throw new NotImplementedException();
    }

    public void verwijderBob(String naam){
        throw new NotImplementedException();
    }

    public void wijzigBob(String bobNaam, String naam, Vak vak){
        throw new NotImplementedException();
    }




}
