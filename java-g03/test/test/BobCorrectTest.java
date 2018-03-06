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
public class BobCorrectTest {

    private String naam;
    private List<Oefening> oefeningen;
    private List<Actie> acties;
    private List<Toegangscode> codes;
    private Vak vak;

    public BobCorrectTest(String naam, List<Oefening> oefeningen, List<Actie> acties, List<Toegangscode> codes, Vak vak) {
        this.naam = naam;
        this.oefeningen = oefeningen;
        this.acties = acties;
        this.codes = codes;
        this.vak = vak;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getTestParameters(){
        return Arrays.asList(
                new Object[][]{
                        {"Correcte naam", new ArrayList<Oefening>(), new ArrayList<Actie>(), new ArrayList<Toegangscode>(), new Vak("Wiskunde", "rood")},
                        {"Correcte Naam", new ArrayList<Oefening>(), new ArrayList<Actie>(), new ArrayList<Toegangscode>(), new Vak("NietWiskunde", "rood")},
                        {"Correcte Naam", new ArrayList<Oefening>(), new ArrayList<Actie>(), new ArrayList<Toegangscode>(), new Vak("Wiskunde", "Blauf")},
                });
    }

    @Test(expected = IllegalArgumentException.class)
    public void aanmakenBobWerktNietTest() {
        new Bob(naam, oefeningen, acties, codes, vak);
    }

}