package domein;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import persistentie.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;

public class OefeningBeheerder extends Observable {

    private Oefening oefening;
    private GenericDao<Oefening> oefeningRepo;
    private GenericDao<Vak> vakRepo;
    private FileTransfer fileTransfer;

    private FilteredList<Oefening> oefeningList;

    private final Comparator<Oefening> byOefeningNaam = (o1, o2) -> o1.getNaam().compareToIgnoreCase(o2.getNaam());

    public OefeningBeheerder() {
        setOefeningRepo(new OefeningDaoJpa());
        setVakRepo(new GenericDaoJpa<>(Vak.class));
        fileTransfer = new FileTransfer();
    }

    public OefeningBeheerder(OefeningDao mock) {
        this.oefeningRepo = mock;
    }

    public void setOefeningRepo(OefeningDao mock) {
        oefeningRepo = mock;
    }

    public void setVakRepo(GenericDao<Vak> mock) {
        vakRepo = mock;
    }

    public Oefening getOefening() {
        return oefening;
    }

    public void setOefening(Oefening oefening) {
        this.oefening = oefening;
        setChanged();
        notifyObservers(oefening);
    }

    private Comparator<Oefening> getByOefeningNaam() {
        return byOefeningNaam;
    }

    /**
     * @param oef
     */
    public void verwijderOefening() {
        GenericDaoJpa.startTransaction();
        oefeningRepo.delete(oefening);
        GenericDaoJpa.commitTransaction();
        oefeningList = null;
        getOefeningList();

    }

    /**
     * @param oefeningNaam
     * @param naam
     * @param opgavePath
     * @param antwoord
     * @param feedback
     * @param groepsbewerkingen
     * @param vak
     */

    public void wijzigOefening(String naam, File opgaveFile, String antwoord, File feedbackFile, ArrayList<Groepsbewerking> groepsbewerkingen, Vak vak) {
        GenericDaoJpa.startTransaction();
        oefeningRepo.delete(oefening);
        GenericDaoJpa.commitTransaction();
        createOefening(naam, opgaveFile, antwoord, feedbackFile, groepsbewerkingen, vak);
    }


    public ObservableList<Oefening> geefOefeningen() {
        return new SortedList<>(getOefeningList(), getByOefeningNaam());
    }

    private ObservableList<Oefening> getOefeningList() {
        if (oefeningList == null) {
            oefeningList = new FilteredList<>(FXCollections.observableList(oefeningRepo.findAll()), e -> true);
        }

        return oefeningList;
    }

    /**
     * @param naam
     * @param opgaveFile
     * @param antwoord
     * @param feedbackFile
     * @param groepsbewerkingen
     * @param vak
     */
    public void createOefening(String naam, File opgaveFile, String antwoord, File feedbackFile, List<Groepsbewerking> groepsbewerkingen, Vak vak) {

        Oefening oef = new Oefening(naam, opgaveFile.getName(), antwoord, feedbackFile.getName(), groepsbewerkingen, vak);

        if (oefeningRepo.exists(oef.getNaam())) {
            throw new IllegalArgumentException("Oefening met naam: " + naam + " bestaat al");
        } else {
            GenericDaoJpa.startTransaction();
            oefeningRepo.insert(oef);
            GenericDaoJpa.commitTransaction();
            fileTransfer.connect();
            fileTransfer.uploadFile(opgaveFile.getPath(), opgaveFile.getName());
            fileTransfer.uploadFile(feedbackFile.getPath(), feedbackFile.getName());
            fileTransfer.disconnect();
            oefeningList = null;
            getOefeningList();
        }
    }

    /**
     * @param naam
     */
    public Oefening geefOefening(String naam) {
        return oefeningRepo.get(naam);
    }

    /**
     * @param oefeningNaam
     */
    public void changeFilter(String oefeningNaam, List<String> vakken) {
        oefeningList.setPredicate(oefening -> {

            boolean oefeningNaamLeeg = oefeningNaam == null || oefeningNaam.isEmpty();
            boolean vakkenLeeg = vakken == null || vakken.isEmpty();

            if (oefeningNaamLeeg && vakkenLeeg) {
                return true;
            }

            String lowerCaseOefeningNaam = "";

            if (!oefeningNaamLeeg) {
                lowerCaseOefeningNaam = oefeningNaam.toLowerCase();
            }

            boolean conditieOefeningNaam = oefeningNaamLeeg ? false : oefening.getNaam().toLowerCase().contains(lowerCaseOefeningNaam);
            boolean conditieVakken = vakkenLeeg ? false : vakken.stream().anyMatch(t -> t.equals(oefening.getVak().getNaam()));

            if (oefeningNaamLeeg) {
                return conditieVakken;
            } else if (vakkenLeeg) {
                return conditieOefeningNaam;
            }

            return conditieOefeningNaam && conditieVakken;
        });
    }


    /**
     * @param fileName
     */
    public File geefPdf(String fileName) {
        // TODO  - retrieve file
        throw new UnsupportedOperationException();
    }


}