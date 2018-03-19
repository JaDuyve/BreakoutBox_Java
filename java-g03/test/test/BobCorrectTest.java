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

    public BobCorrectTest(String naam, List<Oefening> oefeningen, List<Actie> acties, List<Toegangscode> codes) {
        this.naam = naam;
        this.oefeningen = oefeningen;
        this.acties = acties;
        this.codes = codes;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getTestParameters(){
        return Arrays.asList(
                new Object[][]{
                     //   {"Correcte naam", new ArrayList<Oefening>(Arrays.asList(new Oefening("oefening3", "opgavePath", "antwoord", "feedbackPath", new ArrayList<Groepsbewerking>(Arrays.asList(new Groepsbewerking("groepsbewerking1", "test", "2", Bewerking.AFTREKKING),new Groepsbewerking("groepsbewerking2", "test", "2", Bewerking.AFTREKKING))), new Vak("wiskunde", "red"))), new ArrayList<Actie>(Arrays.asList(new Actie("potje", "Zoek blauw potje"), new Actie("blad", "zoek het gele blad"), new Actie("doos", "zoek de rode doos"))), new ArrayList<Toegangscode>())},
                        {"Correcte Naam", new ArrayList<Oefening>(), new ArrayList<Actie>(), new ArrayList<Toegangscode>()},
                        {"Correcte Naam", new ArrayList<Oefening>(), new ArrayList<Actie>(), new ArrayList<Toegangscode>()},
                });
    }

    @Test(expected = IllegalArgumentException.class)
    public void aanmakenBobWerktNietTest() {
        new Bob(naam, oefeningen, acties);
    }

}