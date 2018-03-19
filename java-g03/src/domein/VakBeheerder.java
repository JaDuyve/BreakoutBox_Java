package domein;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import org.apache.commons.collections4.iterators.FilterListIterator;
import persistentie.GenericDao;
import persistentie.GenericDaoJpa;

import java.util.Comparator;
import java.util.List;

public class VakBeheerder {

    private List<Vak> vakken;
    private Vak vak;
    private FilteredList<Vak> filtVakken;

    private GenericDao<Vak> vakRepo;

    private Comparator<Vak> byVakNaam = (v1, v2) -> v1.getNaam().compareToIgnoreCase(v2.getNaam());

    public VakBeheerder() {
        setVakRepo(new GenericDaoJpa(Vak.class));
    }

    public void setVakRepo(GenericDao<Vak> mock){
        vakRepo = mock;
    }

    private List<Vak> getVakken(){
        if (vakken == null){
            vakken = vakRepo.findAll();
        }

        return vakken;
    }

    public ObservableList<Vak> geefVakken(){
        filtVakken = new FilteredList<>(FXCollections.observableList(getVakken()), e -> true);
        return new SortedList<>(filtVakken, byVakNaam);
    }

    public void changeFilter(String naam) {
        filtVakken.setPredicate(vak -> {

            if (naam == null || naam.isEmpty()) {
                return true;
            }

            return vak.getNaam().toLowerCase().contains(naam.toLowerCase());
        });
    }

    public void verwijderVak() {
        GenericDaoJpa.startTransaction();
        vakRepo.delete(vak);
        GenericDaoJpa.commitTransaction();
        vakken.remove(vak);
        vak = null;

    }

    public void createVak(String naam, String color) {
        Vak vak = new Vak(naam, color);

        if (vakRepo.exists(vak.getNaam())) {
            throw new IllegalArgumentException("Vak met naam: " + naam + " bestaat al");
        } else {
            GenericDaoJpa.startTransaction();
            vakRepo.insert(vak);
            GenericDaoJpa.commitTransaction();
            vakken.add(vak);
        }
    }

    public void wijzigeVak(String naam, String color) {
        if (!vak.getNaam().equals(naam)) {
            createVak(naam, color);
            GenericDaoJpa.startTransaction();
            vakRepo.delete(vak);
            GenericDaoJpa.commitTransaction();
        } else {
            GenericDaoJpa.startTransaction();
            if (!color.equals(vak.getKleur())) {
                vak.setKleur(color);
            }

            GenericDaoJpa.commitTransaction();
        }


    }

    public Vak getVak() {
        return vak;
    }

    public void setVak(Vak vak) {
        this.vak = vak;
    }
}
