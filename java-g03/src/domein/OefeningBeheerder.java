package domein;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import org.eclipse.persistence.jpa.config.Array;
import persistentie.*;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class OefeningBeheerder extends Observable {

    private Oefening oefening;
    private GenericDao<Oefening> oefeningRepo;
    private GenericDao<Bob> bobRepo;
    private FileTransfer fileTransfer;

    private List<Oefening> oefeningen;
    private FilteredList<Oefening> filtOefeningen;

    private Job jobs;

    private final Comparator<Oefening> byOefeningNaam = (o1, o2) -> o1.getNaam().compareToIgnoreCase(o2.getNaam());

    public OefeningBeheerder() {
        setOefeningRepo(new GenericDaoJpa(Oefening.class));
        setBobRepo(new GenericDaoJpa(Bob.class));
        fileTransfer = new FileTransfer();
        getOefeningList();

        jobs = new Job();
        new Thread(new FileTransfer(jobs)).start();

    }

    public void setBobRepo(GenericDaoJpa mock) {
        this.bobRepo = mock;
    }

    public OefeningBeheerder(GenericDao mock) {
        this.oefeningRepo = mock;
    }

    public void setOefeningRepo(GenericDao mock) {
        oefeningRepo = mock;
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


    public void verwijderOefening() {
        controleerOefInBob(oefening);


        oefeningRepo.delete(oefening);

        jobs.plaatsJob(new ArrayList<>(Arrays.asList("DELETE", oefening.getOpgave())));
        jobs.plaatsJob(new ArrayList<>(Arrays.asList("DELETE", oefening.getFeedback())));
        oefeningen.remove(oefening);
        oefening = null;

    }

    /**
     * @param naam
     * @param opgaveFile
     * @param antwoord
     * @param feedbackFile
     * @param groepsbewerkingen
     * @param vak
     */

    public void wijzigOefening(String naam, File opgaveFile, String antwoord, File feedbackFile, List<Groepsbewerking> groepsbewerkingen, List<Doelstellingscode> doelstellingen, Vak vak, int tijdsLimiet) {
        controleerOefInBob(oefening);

        if (!naam.equals(oefening.getNaam())) {
            createOefening(naam, opgaveFile, antwoord, feedbackFile, groepsbewerkingen, doelstellingen, vak, tijdsLimiet);

            oefeningRepo.delete(oefening);

        } else {
            if (!oefening.getOpgave().equals(opgaveFile.getName())) {
                jobs.plaatsJob(new ArrayList<>(Arrays.asList("DELETE", oefening.getOpgave())));
                oefening.setOpgave("Opgave_" + naam + "_" + opgaveFile.getName());
                jobs.plaatsJob(new ArrayList<>(Arrays.asList("UPLOAD", opgaveFile.getPath(), oefening.getOpgave())));

            }
            if (!oefening.getFeedback().equals(feedbackFile.getName())) {

                jobs.plaatsJob(new ArrayList<>(Arrays.asList("DELETE", oefening.getFeedback())));
                oefening.setFeedback("Feedback_" + naam + "_" + feedbackFile.getName());
                jobs.plaatsJob(new ArrayList<>(Arrays.asList("UPLOAD", feedbackFile.getPath(), oefening.getFeedback())));

            }
            if (!antwoord.equals(oefening.getAntwoord())) {
                oefening.setAntwoord(antwoord);
            }
            if (tijdsLimiet != oefening.getTijdsLimiet()) {
                oefening.setTijdsLimiet(tijdsLimiet);
            }
            if (!vak.getNaam().equals(oefening.getVak().getNaam())) {
                oefening.setVak(vak);
            }
            if (!groepsbewerkingen.containsAll(oefening.getLijstGroepsbewerkingen())) {
                oefening.setLijstGroepsbewerkingen(groepsbewerkingen);
            }
            if (!doelstellingen.containsAll(oefening.getDoelstellingscodes())) {
                oefening.setDoelstellingscodes(doelstellingen);
            }

            oefeningRepo.update(oefening);

        }
    }


    public ObservableList<Oefening> geefOefeningen() {
        filtOefeningen = new FilteredList<>(FXCollections.observableList(getOefeningList()), e -> true);
        return new SortedList<>(filtOefeningen, getByOefeningNaam());
    }

    private List<Oefening> getOefeningList() {
        if (oefeningen == null) {
            oefeningen = oefeningRepo.findAll();
        }

        return oefeningen;
    }

    private void controleerOefInBob(Oefening oef) {
        boolean result = bobRepo.findAll().stream()
                .filter(bob -> bob.getLijstOefeningen().contains(oef)).collect(Collectors.toList()).size() != 0;


        if (result) {
            throw new IllegalArgumentException("Oefening is nog gelinkte met een Breakout Box, hierdoor is het niet mogelijk om deze oefening te verwijderen.");
        }
    }

    /**
     * @param naam
     * @param opgaveFile
     * @param antwoord
     * @param feedbackFile
     * @param groepsbewerkingen
     * @param vak
     */
    public void createOefening(String naam, File opgaveFile, String antwoord, File feedbackFile, List<Groepsbewerking> groepsbewerkingen, List<Doelstellingscode> doelstellingen, Vak vak, int tijdsLimiet) {
        Oefening oef;

        if (opgaveFile == null) {
            throw new IllegalArgumentException("Er moet een opgave pdf meegegeven worden.");
        }
        if (feedbackFile == null) {
            throw new IllegalArgumentException("Er moet een feedback pdf meegegeven worden.");
        }

        oef = new Oefening(naam, "Opgave_" + naam + "_" + opgaveFile.getName(), antwoord, "Feedback_" + naam + "_" + feedbackFile.getName(), groepsbewerkingen, doelstellingen, vak, tijdsLimiet);

        if (oefeningRepo.exists(oef.getNaam())) {
            throw new IllegalArgumentException("Oefening met naam: " + naam + " bestaat al");
        } else {
            oefeningRepo.insert(oef);

            jobs.plaatsJob(new ArrayList<>(Arrays.asList("UPLOAD", opgaveFile.getPath(), oef.getOpgave())));
            jobs.plaatsJob(new ArrayList<>(Arrays.asList("UPLOAD", feedbackFile.getPath(), oef.getFeedback())));

            oefeningen.add(oef);
        }
    }


    /**
     * @param oefeningNaam
     */
    public void changeFilter(String oefeningNaam, List<String> vakken, List<String> doelstellingscodes) {
        filtOefeningen.setPredicate(oefening -> {

            boolean oefeningNaamLeeg = oefeningNaam == null || oefeningNaam.isEmpty();
            boolean vakkenLeeg = vakken == null || vakken.isEmpty();
            boolean doelstellingenLeeg = doelstellingscodes == null || doelstellingscodes.isEmpty();

            if (oefeningNaamLeeg && vakkenLeeg && doelstellingenLeeg) {
                return true;
            }

            String lowerCaseOefeningNaam = "";

            if (!oefeningNaamLeeg) {
                lowerCaseOefeningNaam = oefeningNaam.toLowerCase();
            }

            boolean conditieOefeningNaam = oefeningNaamLeeg ? false : oefening.getNaam().toLowerCase().contains(lowerCaseOefeningNaam);
            boolean conditieVakken = vakkenLeeg ? false : vakken.stream().anyMatch(t -> t.equals(oefening.getVak().getNaam()));
            boolean conditieDoelstellingen = doelstellingenLeeg ? false : doelstellingscodes.stream().anyMatch(d -> oefening.getDoelstellingscodes().stream().anyMatch(dc -> dc.getCode().equals(d)));


            if (doelstellingenLeeg && oefeningNaamLeeg) {
                return conditieVakken;
            }
            if (doelstellingenLeeg && vakkenLeeg) {
                return conditieOefeningNaam;
            }
            if (oefeningNaamLeeg && vakkenLeeg) {
                return conditieDoelstellingen;
            }
            if (oefeningNaamLeeg) {
                return conditieVakken && conditieDoelstellingen;
            }
            if (vakkenLeeg) {
                return conditieOefeningNaam && conditieDoelstellingen;
            }
            if (doelstellingenLeeg) {
                return conditieOefeningNaam && conditieDoelstellingen;
            }

            return conditieOefeningNaam && conditieVakken && conditieDoelstellingen;
        });

        if (filtOefeningen.size() == 0){
            setOefening(null);
        }
    }


    /**
     * @param fileName
     */
    public File geefFile(String fileName) {
        fileTransfer.connect();
        File file = fileTransfer.retrieveFile(fileName, fileName);
        fileTransfer.disconnect();
        return file;
    }


}