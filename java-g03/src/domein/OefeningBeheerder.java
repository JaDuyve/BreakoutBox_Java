package domein;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import persistentie.GenericDao;
import persistentie.GenericDaoJpa;
import persistentie.OefeningDao;
import persistentie.OefeningDaoJpa;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OefeningBeheerder {

    private Oefening oefening;
    private OefeningDao oefeningRepo;
    private GenericDao<Vak> vakRepo;

    private FilteredList<Oefening> oefeningList;

    private final Comparator<Oefening> byOefeningNaam = (o1, o2) -> o1.getNaam().compareToIgnoreCase(o2.getNaam());

    public OefeningBeheerder() {
        setOefeningRepo(new OefeningDaoJpa());
        setVakRepo(new GenericDaoJpa<>(Vak.class));


    }

    public OefeningBeheerder(OefeningDao mock){
        this.oefeningRepo = mock;
    }

    public void setOefeningRepo(OefeningDao mock) {
        oefeningRepo = mock;
    }

    public void setVakRepo(GenericDao<Vak> mock){
        vakRepo = mock;
    }

    /**
     * @param naam
     */
    public void verwijderOefening(String naam) {

        oefeningRepo.deleteOefeningByName(naam);
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
    public void wijzigOefening(String oefeningNaam, String naam, String opgavePath, String antwoord, String feedback, ArrayList<Groepsbewerking> groepsbewerkingen, Vak vak) {
        if (getOefeningList().stream().filter(i -> i.getNaam().equals(oefeningNaam)).findFirst().isPresent()) { //MOET NOG AAN DE OEFENINGENDAO GEVRAAGD WORDEN
            //TODO - OEFENING AANMAKEN IN WIJZIGINGOEFENING
            throw new UnsupportedOperationException();
        } else {
            //oefening = oefeningMapper.geefOefeningen().stream().filter(i -> i.getNaam().equals(oefeningNaam)).findAny().get();
            oefening = oefeningRepo.get(oefeningNaam);
            oefening.setNaam(naam);///???????
            oefening.setOpgave(opgavePath);
            oefening.setFeedback(feedback);
            oefening.setVak(vak);
            oefening.setLijstGroepsbewerkingen(groepsbewerkingen);
            oefeningRepo.update(oefening);
        }
    }

    public ObservableList<Vak> geefVakken() {
        return FXCollections.observableList(vakRepo.findAll());
    }

    public ObservableList<Groepsbewerking> geefGroepsbewerkingen() {
        // TODO - implement OefeningBeheerder.geefGroepsbewerkingen
        throw new UnsupportedOperationException();
    }

    public ObservableList<Oefening> geefOefeningen() {
        return new SortedList<>(getOefeningList(), byOefeningNaam);
    }

    private ObservableList<Oefening> getOefeningList(){
        if (oefeningList == null){
            oefeningList = new FilteredList<>(FXCollections.observableList(oefeningRepo.findAll()));
        }

        return oefeningList;
    }

    /**
     * @param naam
     * @param opgavePath
     * @param antwoord
     * @param feedback
     * @param groepsbewerkingen
     * @param vak
     */
    public void createOefening(String naam, String opgavePath, String antwoord, String feedback, List<Groepsbewerking> groepsbewerkingen, Vak vak) {
        oefeningList.add(new Oefening(naam, opgavePath, antwoord, feedback, groepsbewerkingen, vak));
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

            if (oefeningNaamLeeg && vakkenLeeg){
                return true;
            }

            String lowerCaseOefeningNaam = "";

            if (!oefeningNaamLeeg){
                lowerCaseOefeningNaam = oefeningNaam.toLowerCase();
            }

            boolean conditieOefeningNaam = oefeningNaamLeeg ? false: oefening.getNaam().toLowerCase().contains(lowerCaseOefeningNaam);
            boolean conditieVakken = vakkenLeeg ? false: vakken.stream().anyMatch(t -> t.equals(oefening.getVak().getNaam()));

            if (oefeningNaamLeeg){
                return conditieOefeningNaam;
            }else if (vakkenLeeg){
                return conditieVakken;
            }

            return conditieOefeningNaam && conditieVakken;
        });
    }



    /**
     * @param pathName
     */
    public File geefOpgave(String pathName) {
        // TODO - implement OefeningBeheerder.geefOpgave
        throw new UnsupportedOperationException();
    }

    public Oefening kopieOefening(String naam)
    {
        // TODO - implement OefeningBeheerder.kopieOefening
        throw new UnsupportedOperationException();//is dit nodig? Dient geefoefening hier niet voor, die voor kopieOefening en wijzigig oefening gebruikt word om de details op te halen.
    }



}