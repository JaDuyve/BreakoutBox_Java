package domein;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import org.mockito.internal.util.collections.ListUtil;
import persistentie.GenericDao;
import persistentie.GenericDaoJpa;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.time.LocalDate;
import java.util.*;

public class SessieBeheerder extends Observable {
    private Comparator<Sessie> bySessieNaam = (s1, s2) -> s1.getNaam().compareToIgnoreCase(s2.getNaam());


    private Sessie sessie;
    private GenericDao<Sessie> sessieRepo;
    private List<Sessie> sessies;
    private FilteredList<Sessie> filtSsessie;


    public SessieBeheerder() {
        sessieRepo = new GenericDaoJpa<>(Sessie.class);
        getSessieList();

    }

    public void create(String naam, Date startDate, Bob bob, File groepen, boolean contactLeer){
        sessie = new Sessie(naam, startDate, bob, groepen, contactLeer);

        if (sessieRepo.exists(sessie.getNaam())) {
            throw new IllegalArgumentException("Breakout Box met naam: " + naam + " bestaat al");
        } else {
            sessieRepo.insert(sessie);
            sessies.add(sessie);
        }
    }



    private List<Sessie> getSessieList(){
        if (sessies == null){
            sessies = sessieRepo.findAll();
        }

        return sessies;
    }

    public void setSessie(Sessie sessie) {
        this.sessie = sessie;
    }

    public ObservableList<Sessie> geefSessies(){
        filtSsessie = new FilteredList<>(FXCollections.observableList(getSessieList()), e -> true);
        return new SortedList<>(filtSsessie, bySessieNaam);
    }

    public void changeFilter(String naam){
        filtSsessie.setPredicate(sessie -> {
            if (naam == null || naam.isEmpty()){
                return true;
            }

            return sessie.getNaam().toLowerCase().contains(naam.toLowerCase());
        });
    }
    }

