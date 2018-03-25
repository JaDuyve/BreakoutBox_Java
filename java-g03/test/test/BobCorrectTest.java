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

    public BobCorrectTest(String naam, List<Oefening> oefeningen, List<Actie> acties) {
        this.naam = naam;
        this.oefeningen = oefeningen;
        this.acties = acties;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getTestParameters() {
        return Arrays.asList(
                new Object[][]{
                        {"Correcte Naam",
                                new ArrayList<Oefening>(
                                        Arrays.asList(
                                                new Oefening("oefening3", "opgavePath", "antwoord", "feedbackPath",
                                                        new ArrayList<Groepsbewerking>(Arrays.asList(
                                                                new Groepsbewerking("groepsbewerking1", "test", "2", Bewerking.AFTREKKING),
                                                                new Groepsbewerking("groepsbewerking2", "test", "2", Bewerking.AFTREKKING))),
                                                        new ArrayList<Doelstellingscode>(),
                                                        new Vak("wiskunde", "red"), 1),
                                                new Oefening("oefening2", "opPath", "antwoord", "feedPath",
                                                        new ArrayList<Groepsbewerking>(Arrays.asList(
                                                                new Groepsbewerking("groepsbewerking1", "test", "2", Bewerking.AFTREKKING),
                                                                new Groepsbewerking("groepsbewerking2", "test", "2", Bewerking.AFTREKKING))),
                                                        new ArrayList<Doelstellingscode>(),
                                                        new Vak("Getallenleer", "green"), 1)
                                        )
                                ),
                                new ArrayList<Actie>(Arrays.asList(new Actie("zoek doos", "zoek groene doos"))),

                        },
                });
    }

    @Test
    public void aanmakenBobCorrectTest() {
        new Bob(naam, oefeningen, acties);
    }

}