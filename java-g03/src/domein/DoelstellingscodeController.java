package domein;

import javafx.collections.ObservableList;

import java.util.Observer;

public class DoelstellingscodeController
{
    private DoelstellingscodeBeheerder doelstellingscodeBeheerder;

    public DoelstellingscodeController()
    {
        setDoelstellingscodeBeheerder(new DoelstellingscodeBeheerder());
    }

    public void createDoelstellingscode(String code)
    {
        doelstellingscodeBeheerder.createDoelstellingscode(code);
    }

    public void wijzigDoelstellingscode(String code)
    {
        doelstellingscodeBeheerder.wijzigDoelstellingscode(code);
    }

    public void changeFilter(String code)
    {
        doelstellingscodeBeheerder.changeFilter(code);
    }

    public void veranderHuidige(Doelstellingscode doelstellingscode)
    {
        doelstellingscodeBeheerder.setDoelstellingscode(doelstellingscode);
    }

    public ObservableList<Doelstellingscode> geefDoelstellingscodes()
    {
        return doelstellingscodeBeheerder.geefDoelstellingscodes();
    }

    public void verwijderDoelstellingscode()
    {
        doelstellingscodeBeheerder.verwijderDoelstellingscode();
    }

    public void setDoelstellingscodeBeheerder(DoelstellingscodeBeheerder doelstellingscodeBeheerder) {
        this.doelstellingscodeBeheerder = doelstellingscodeBeheerder;
    }

    public void addObservertje(Observer observer) {
        doelstellingscodeBeheerder.addObserver(observer);
    }

}
