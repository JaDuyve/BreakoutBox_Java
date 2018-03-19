package domein;

import javafx.collections.ObservableList;

public class DoelstellingscodeController
{
    private DoelstellingscodeBeheerder doelstellingscodeBeheerder;

    public DoelstellingscodeController(DoelstellingscodeBeheerder doelstellingscodeBeheerder)
    {
        setDoelstellingscodeBeheerder(doelstellingscodeBeheerder);
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
        doelstellingscodeBeheerder.veranderHuidige(doelstellingscode);
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
}
