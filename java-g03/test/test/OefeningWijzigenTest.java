/*
package test;

import domein.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import persistentie.GenericDao;
import persistentie.GenericDaoJpa;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@RunWith(value = Parameterized.class)
public class OefeningWijzigenTest {

    private List<Oefening> oefeningen;
    private OefeningBeheerder oefeningBeheerder;

    private String naam;
    private File opgave;
    private String antwoord;
    private File feedback;
    private List<Groepsbewerking> groepsbewerkingen;
    private List<Doelstellingscode> doelstellingsCodes;

    private GenericDao<Oefening> oefeningRepo;

    public OefeningWijzigenTest(String naam,File opgave, String antwoord, File feedback ,List<Groepsbewerking> groepsbewerkingen, List<Doelstellingscode> doelstellingscodes) {
        this.naam = naam;
        this.opgave = opgave;
        this.antwoord = antwoord;
        this.feedback = feedback;
        this.groepsbewerkingen = groepsbewerkingen;
        this.doelstellingsCodes = doelstellingscodes;
    }

    @Before
    public void before(){
        oefeningRepo = Mockito.mock(GenericDaoJpa.class);
        oefeningBeheerder = new OefeningBeheerder(oefeningRepo);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getTestParameters() {
        return Arrays.asList(
                new Object[][]{
                        {"oefening2"}
                });
    }

    @Test(expected = IllegalArgumentException.class)
    public void wijzigenbob() {
        new ArrayList<>(Arrays.asList(new Oefening("oefening3", "opgavePath", "antwoord", "feedbackPath", new ArrayList<Groepsbewerking>(Arrays.asList(new Groepsbewerking("groepsbewerking1", "test", "2", Bewerking.AFTREKKING),new Groepsbewerking("groepsbewerking2", "test", "2", Bewerking.AFTREKKING))), new Vak("wiskunde", "red")), new Oefening("oefening2", "opgavePath", "antwoord", "feedbackPath", new ArrayList<Groepsbewerking>(Arrays.asList(new Groepsbewerking("groepsbewerking1", "test", "2", Bewerking.AFTREKKING),new Groepsbewerking("groepsbewerking2", "test", "2", Bewerking.AFTREKKING))), new Vak("wiskunde", "red")), new Oefening("oefening1", "opgavePath", "antwoord", "feedbackPath", new ArrayList<Groepsbewerking>(Arrays.asList(new Groepsbewerking("groepsbewerking1", "test", "2", Bewerking.AFTREKKING),new Groepsbewerking("groepsbewerking2", "test", "2", Bewerking.AFTREKKING))), new Vak("wiskunde", "red"))))
    }

}

*/
