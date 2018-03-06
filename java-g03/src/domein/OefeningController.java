package domein;

import gui.OefeningenDetailPaneelController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistentie.GenericDao;
import persistentie.GenericDaoJpa;

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

    public void verwijderOefening(){
        oefeningBeheerder.verwijderOefening();
    }

    public void wijzigOefening(String naam, File opgavefile, String antwoord, File feedbackfile, ArrayList<Groepsbewerking> groepsbewerkingen, Vak vak){
        oefeningBeheerder.wijzigOefening(naam, opgavefile, antwoord, feedbackfile, groepsbewerkingen, vak);
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
        return oefeningBeheerder.geefPdf(pathName);
    }

    public void veranderHuidigeOefening(Oefening oefening) {
        oefeningBeheerder.setOefening(oefening);
    }
    public void addObservertje(OefeningenDetailPaneelController detailPanelController) {
        oefeningBeheerder.addObserver(detailPanelController);
    }
}