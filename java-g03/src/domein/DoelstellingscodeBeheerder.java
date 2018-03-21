package domein;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Scene;
import persistentie.GenericDao;
import persistentie.GenericDaoJpa;

import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

public class DoelstellingscodeBeheerder extends Observable
{
    private List<Doelstellingscode> doelstellingscodes;
    private FilteredList<Doelstellingscode> filtDoelstellingscodes;
    private GenericDao<Doelstellingscode> doelstellingscodeRepo;
    private Doelstellingscode doelstellingscode;
    private final Comparator<Doelstellingscode> byDoelstellingscode = (d1, d2) -> d1.getCode().compareToIgnoreCase(d2.getCode());

    public DoelstellingscodeBeheerder()
    {
        setDoelstellingscodeRepo(new GenericDaoJpa(Doelstellingscode.class));
    }

    public void createDoelstellingscode(String code)
    {
        Doelstellingscode doelstellingscode = new Doelstellingscode(code);

        if(doelstellingscodeRepo.exists(doelstellingscode.getCode()))
        {
            throw new IllegalArgumentException("Doelstellingscode met naam: " + code + " bestaat al");
        } else
        {
            GenericDaoJpa.startTransaction();
            doelstellingscodeRepo.insert(doelstellingscode);
            GenericDaoJpa.commitTransaction();
            doelstellingscodes.add(doelstellingscode);
        }
    }

    public void wijzigDoelstellingscode(String code)
    {
        if(!doelstellingscodeRepo.exists(code))
        {
            throw new IllegalArgumentException("Doelstellingscode met naam: " + code + "bestaat niet");
        } else
        {
            if(!doelstellingscodes.contains(doelstellingscode.getCode()))
            {
                doelstellingscode.setCode(code);
            }
            doelstellingscodeRepo.update(doelstellingscode);
        }
    }

    public void verwijderDoelstellingscode()
    {
        controleerCodeInOefening(doelstellingscode);
        doelstellingscodeRepo.delete(doelstellingscode);
        doelstellingscodes.remove(doelstellingscode);
        doelstellingscode = null;
    }

    public void changeFilter(String code)
    {
        filtDoelstellingscodes.setPredicate(doel -> {
            if(code == null || code.isEmpty())
            {
                return true;
            }

            return doel.getCode().toLowerCase().contains(code.toLowerCase());
        });
    }

    public ObservableList<Doelstellingscode> geefDoelstellingscodes()
    {
        filtDoelstellingscodes = new FilteredList<>(FXCollections.observableList(getDoelstellingscodes()), e -> true);
        return new SortedList<>(filtDoelstellingscodes, byDoelstellingscode);
    }


    public List<Doelstellingscode> getDoelstellingscodes() {
        if (doelstellingscodes == null){
            doelstellingscodes = doelstellingscodeRepo.findAll();
        }

        return doelstellingscodes;
    }

    public void setDoelstellingscode(Doelstellingscode doelstellingscode)
    {
        this.doelstellingscode = doelstellingscode;
        setChanged();
        notifyObservers(doelstellingscode);
    }

    public void setDoelstellingscodeRepo(GenericDao<Doelstellingscode> doelstellingscodeRepo) {
        this.doelstellingscodeRepo = doelstellingscodeRepo;
    }

    private void controleerCodeInOefening(Doelstellingscode code) {
        boolean result = doelstellingscodeRepo.findAll().stream()
                .filter(doel -> doel.getCode().equals(code)).collect(Collectors.toList()).size() != 0;


        if (result) {
            throw new IllegalArgumentException("Doelstellingscode is nog gelinkt met een oefening, hierdoor is het niet mogelijk om deze doelstellingscode te verwijderen.");
        }
    }
}
