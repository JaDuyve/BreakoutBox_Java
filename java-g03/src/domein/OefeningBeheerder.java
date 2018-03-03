package domein;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistentie.GenericDao;
import persistentie.GenericDaoJpa;
import persistentie.OefeningDao;
import persistentie.OefeningDaoJpa;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OefeningBeheerder {

    private Oefening oefening;
    private List<Oefening> oefeningList;
    private OefeningDao oefeningRepo;
    private GenericDao<Vak> vakRepo;




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
        oefeningList = oefeningRepo.findAll();
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
        return FXCollections.unmodifiableObservableList(FXCollections.observableList(vakRepo.findAll()));
    }

    public ObservableList<Groepsbewerking> geefGroepsbewerkingen() {
        // TODO - implement OefeningBeheerder.geefGroepsbewerkingen
        throw new UnsupportedOperationException();
    }

    public ObservableList<Oefening> geefOefeningen() {
        ObservableList<Oefening> obsOef = FXCollections.observableList(getOefeningList());
        return obsOef;
    }

    private List<Oefening> getOefeningList(){
        if (oefeningList == null){
            //oefeningList = oefeningRepo.findAll();
            // TODO - findall uitwerken
            oefeningList = new ArrayList<>();
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
    public void filterOpNaam(String oefeningNaam) {
        // TODO - implement OefeningBeheerder.filterOpNaam
        throw new UnsupportedOperationException();


    }

    /**
     * @param vakNaam
     */
    public void filterOpVak(String vakNaam) {
        // TODO - implement OefeningBeheerder.filterOpVak
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }



}