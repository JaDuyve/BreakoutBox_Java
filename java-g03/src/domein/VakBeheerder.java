package domein;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.paint.Color;
import persistentie.GenericDao;
import persistentie.GenericDaoJpa;

import java.security.SecureRandom;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;

public class VakBeheerder extends Observable {

    private List<Vak> vakken;
    private Vak vak;
    private FilteredList<Vak> filtVakken;
    private static SecureRandom random = new SecureRandom();

    private GenericDao<Vak> vakRepo;

    private Comparator<Vak> byVakNaam = (v1, v2) -> v1.getNaam().compareToIgnoreCase(v2.getNaam());

    public VakBeheerder() {
        setVakRepo(new GenericDaoJpa(Vak.class));
    }

    public void setVakRepo(GenericDao<Vak> mock) {
        vakRepo = mock;
    }

    private List<Vak> getVakken() {
        if (vakken == null) {
            vakken = vakRepo.findAll();
        }

        return vakken;
    }

    public ObservableList<Vak> geefVakken() {
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
        vakRepo.delete(vak);
        vakken.remove(vak);
        vak = null;

    }

    public void createVak(String naam) {



        float r = random.nextFloat() / 2f + 0.5f;
        float g = random.nextFloat() / 2f + 0.5f;
        float b = random.nextFloat() / 2f + 0.5f;
        Color color = Color.color(r, g, b);
        Vak vak = new Vak(naam, color.toString());

        if (vakRepo.exists(vak.getNaam())) {
            throw new IllegalArgumentException("Vak met naam: " + naam + " bestaat al");
        } else {
            vakRepo.insert(vak);
            vakken.add(vak);
        }
    }

    public void wijzigeVak(String naam) {
        if (!vak.getNaam().equals(naam)) {
            createVak(naam);
            vakRepo.delete(vak);
        }

    }

    public Vak getVak() {
        return vak;
    }

    public void setVak(Vak vak) {
        this.vak = vak;
        setChanged();
        notifyObservers(vak);

    }
}
