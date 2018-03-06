package domein;

import gui.OefeningenDetailPaneelController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistentie.GenericDao;
import persistentie.GenericDaoJpa;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class OefeningController
{
    private GenericDao<Vak> vakRepo;
    private GenericDao<Groepsbewerking> groepsbewerkingRepo;

    private OefeningBeheerder oefeningBeheerder = new OefeningBeheerder();

    public OefeningController() {
        vakRepo = new GenericDaoJpa<>(Vak.class);
        groepsbewerkingRepo = new GenericDaoJpa<>(Groepsbewerking.class);
    }

    public void verwijderOefening(Oefening oef){
        oefeningBeheerder.verwijderOefening(oef);
    }

    public void wijzigOefening(String oefeningNaam, String naam, String opgavePath, String antwoord, String feedbackPath, ArrayList<Groepsbewerking> groepsbewerkingen, Vak vak){
        oefeningBeheerder.wijzigOefening(oefeningNaam, naam, opgavePath, antwoord, feedbackPath, groepsbewerkingen, vak);
    }

    public ObservableList<Vak> geefVakken(){
        return FXCollections.observableList(vakRepo.findAll());
    }

    public ObservableList<Groepsbewerking> geefGroepsbewerkingen(){
        return FXCollections.observableArrayList(groepsbewerkingRepo.findAll());

    }

    public void createOefening(String naam, File opgaveFile,String antwoord, File feedbackFile, List<Groepsbewerking> groepsbewerkingen, Vak vak){
        oefeningBeheerder.createOefening(naam, opgaveFile, antwoord, feedbackFile, groepsbewerkingen, vak);
    }

    public ObservableList<Oefening> geefOefeningen(){
        return oefeningBeheerder.geefOefeningen();
    }

    public void changeFilter(String oefeningNaam, List<String> vakken){
        oefeningBeheerder.changeFilter(oefeningNaam, vakken);
    }


    public File GeefFile(String pathName){
        throw new NotImplementedException();
    }

    public void veranderHuidigeOefening(Oefening oefening) {
        oefeningBeheerder.setOefening(oefening);
    }
    public void addObservertje(OefeningenDetailPaneelController detailPanelController) {
        oefeningBeheerder.addObserver(detailPanelController);
    }
}