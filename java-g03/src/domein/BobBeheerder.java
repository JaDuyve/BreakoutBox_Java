package domein;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class BobBeheerder {

    private Bob bob;
    private FilteredList<Bob> bobs;

    public BobBeheerder(){
    };

    public Bob geefBob(String naam){
        throw new NotImplementedException();
    }

    public ObservableList<Bob> geefBobs(){
        throw new NotImplementedException();
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
