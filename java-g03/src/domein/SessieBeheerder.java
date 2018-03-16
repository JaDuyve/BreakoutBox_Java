package domein;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
    private FilteredList<Sessie> sessies;


    public SessieBeheerder() {
        sessieRepo = new GenericDaoJpa<>(Sessie.class);
        setSessieList();

    }

    public void create(String naam, Date startDate, Bob bob, File groepen, boolean contactLeer){
        sessie = new Sessie(naam, startDate, bob, groepen, contactLeer);
        sessies.add(sessie);
    }



    private ObservableList<Sessie> setSessieList(){
        if (sessies == null){
            sessies = new FilteredList<>(FXCollections.observableArrayList(sessieRepo.findAll()));
        }

        return sessies;
    }

    public void setSessie(Sessie sessie) {
        this.sessie = sessie;
    }

    public ObservableList<Sessie> geefSessies(){
        return new SortedList<>(sessies, bySessieNaam);
    }

    public void changeFilter(String naam){
        sessies.setPredicate(sessie -> {
            if (naam == null || naam.isEmpty()){
                return true;
            }

            return sessie.getNaam().toLowerCase().contains(naam.toLowerCase());
        });
    }
    }

