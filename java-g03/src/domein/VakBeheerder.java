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
    private GenericDao<Oefening> oefeningRepo;

    private Comparator<Vak> byVakNaam = (v1, v2) -> v1.getNaam().compareToIgnoreCase(v2.getNaam());

    public VakBeheerder() {
        setVakRepo(new GenericDaoJpa(Vak.class));
        oefeningRepo = new GenericDaoJpa<>(Oefening.class) ;
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
        controleerVakInOef(vak);
        GenericDaoJpa.startTransaction();
        vakRepo.delete(vak);
        GenericDaoJpa.commitTransaction();
        vakken.remove(vak);
        vak = null;

    }
    /*private String getMatColor(String typeColor)
    {
        String returnColor = Color.BLACK.toString();
        int arrayId = getResources().getIdentifier("mdcolor_" + typeColor, "array", getApplicationContext().getPackageName());

        if (arrayId != 0)
        {
            TypedArray colors = getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * colors.length());
            returnColor = colors.getColor(index, Color.BLACK);
            colors.recycle();
        }
        return returnColor;
    }*/
    public void createVak(String naam) {

        //txt.setTextColor(getMatColor("500"));


        float r = random.nextFloat() / 2f + 0.5f;
        float g = random.nextFloat() / 2f + 0.5f;
        float b = random.nextFloat() / 2f + 0.5f;
        Color color = Color.color(r, g, b);
        Vak vak = new Vak(naam, color.toString());

        if (vakRepo.exists(vak.getNaam())) {
            throw new IllegalArgumentException("Vak met naam: " + naam + " bestaat al");
        } else {
            GenericDaoJpa.startTransaction();
            vakRepo.insert(vak);
            GenericDaoJpa.commitTransaction();
            vakken.add(vak);
        }
    }

    public void wijzigeVak(String naam) {
        controleerVakInOef(vak);
        if (!vak.getNaam().equals(naam)) {
            createVak(naam);
            GenericDaoJpa.startTransaction();
            vakRepo.delete(vak);
            GenericDaoJpa.commitTransaction();
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

    private void controleerVakInOef(Vak vak) {
        boolean result = false;
        for (Oefening oef : oefeningRepo.findAll()){
            result = oef.getVak().getNaam().equalsIgnoreCase(vak.getNaam());

            if (result){
                break;
            }
        }


        if (result) {
            throw new IllegalArgumentException("Oefening is nog gelinkte met een Oefening, hierdoor is het niet mogelijk om deze Vak te verwijderen.");
        }
    }
}
