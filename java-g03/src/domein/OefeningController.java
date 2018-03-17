package domein;

import gui.OefeningenDetailPaneelController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistentie.GenericDao;
import persistentie.GenericDaoJpa;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class OefeningController {
    private GenericDao<Vak> vakRepo;
    private GenericDao<Groepsbewerking> groepsbewerkingRepo;
    private GenericDao<Doelstellingscode> doelstellingscodeRepo;

    private OefeningBeheerder oefeningBeheerder = new OefeningBeheerder();

    public OefeningController() {
        vakRepo = new GenericDaoJpa<>(Vak.class);
        groepsbewerkingRepo = new GenericDaoJpa<>(Groepsbewerking.class);
        doelstellingscodeRepo = new GenericDaoJpa<>(Doelstellingscode.class);
    }

    public void verwijderOefening() {
        oefeningBeheerder.verwijderOefening();
    }

    public Oefening geefOefening() {
        return oefeningBeheerder.getOefening();
    }

    public void wijzigOefening(String naam, File opgavefile, String antwoord, File feedbackfile, List<Groepsbewerking> groepsbewerkingen, List<Doelstellingscode> doelstellingen,Vak vak) {
        oefeningBeheerder.wijzigOefening(naam, opgavefile, antwoord, feedbackfile, groepsbewerkingen, doelstellingen,vak);
    }

    public ObservableList<Vak> geefVakken() {
        return FXCollections.observableList(vakRepo.findAll());
    }

    public ObservableList<Groepsbewerking> geefGroepsbewerkingen() {
        return FXCollections.observableArrayList(groepsbewerkingRepo.findAll());

    }

    public ObservableList<Doelstellingscode> geefDoelstelingscodes(){
        return FXCollections.observableArrayList(doelstellingscodeRepo.findAll());
    }

    public void createOefening(String naam, File opgaveFile, String antwoord, File feedbackFile, List<Groepsbewerking> groepsbewerkingen, List<Doelstellingscode> doelstellingen,Vak vak) {
        oefeningBeheerder.createOefening(naam, opgaveFile, antwoord, feedbackFile, groepsbewerkingen, doelstellingen,vak);
    }

    public ObservableList<Oefening> geefOefeningen() {
        return oefeningBeheerder.geefOefeningen();
    }

    public void changeFilter(String oefeningNaam, List<String> vakken, List<String> doelstellingen) {
        oefeningBeheerder.changeFilter(oefeningNaam, vakken, doelstellingen);
    }


    public File geefFile(String pathName) {
        return oefeningBeheerder.geefFile(pathName);
    }

    public void veranderHuidigeOefening(Oefening oefening) {
        oefeningBeheerder.setOefening(oefening);
    }

    public void addObservertje(OefeningenDetailPaneelController detailPanelController) {
        oefeningBeheerder.addObserver(detailPanelController);
    }
}