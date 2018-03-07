package test;

import domein.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(value = Parameterized.class)
public class BobFoutiefTest {

    private String naam;
    private List<Oefening> oefeningen;
    private List<Actie> acties;
    private List<Toegangscode> codes;

    public BobFoutiefTest(String naam, List<Oefening> oefeningen, List<Actie> acties, List<Toegangscode> codes) {
        this.naam = naam;
        this.oefeningen = oefeningen;
        this.acties = acties;
        this.codes = codes;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getTestParameters(){
        return Arrays.asList(
                new Object[][]{
                        {"", new ArrayList<Oefening>(), new ArrayList<Actie>(), new ArrayList<Toegangscode>()},
                       // {"CorrecteNaam", new ArrayList<Oefening>(new Oefening("naam", "opgave", "antwoord", "esf", new ArrayList<Groepsbewerking>(new Groepsbewerking("ha", "ha", "ha", Bewerking.AFTREKKING)),  new Vak("Wiskunde", "rood"))), new ArrayList<Actie>(), new ArrayList<Toegangscode>(), new Vak("Wiskunde", "rood")},
                        {"", null, new ArrayList<Actie>(), null},
                        {"", null, null, null},
                        {"Correcte Naam", new ArrayList<Oefening>(), new ArrayList<Actie>(), new ArrayList<Toegangscode>()},
                        {"Correcte Naam", new ArrayList<Oefening>(), new ArrayList<Actie>(), new ArrayList<Toegangscode>()},
                });
    }

    @Test(expected = IllegalArgumentException.class)
    public void aanmakenBobWerktNietTest() {
        new Bob(naam, oefeningen, acties, codes);
    }

}