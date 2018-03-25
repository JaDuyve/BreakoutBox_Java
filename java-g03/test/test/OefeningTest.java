package test;

import domein.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OefeningTest {

    @Test
    public void aanmakenOefeningSuccesTest(){
        List<Doelstellingscode> doel = new ArrayList<>();
        List<Groepsbewerking> groeps = new ArrayList<Groepsbewerking>(Arrays.asList(
                new Groepsbewerking("groepsbewerking1", "test", "2", Bewerking.AFTREKKING),
                new Groepsbewerking("groepsbewerking2", "test", "2", Bewerking.AFTREKKING)));

        Oefening oef = new Oefening("oefening3", "opgavePath", "antwoord", "feedbackPath",
                new ArrayList<Groepsbewerking>(Arrays.asList(
                        new Groepsbewerking("groepsbewerking1", "test", "2", Bewerking.AFTREKKING),
                        new Groepsbewerking("groepsbewerking2", "test", "2", Bewerking.AFTREKKING))),
                new ArrayList<Doelstellingscode>(),
                new Vak("wiskunde", "red"), 1);

        Assert.assertEquals("oefening3", oef.getNaam());
        Assert.assertEquals("opgavePath", oef.getOpgave());
        Assert.assertEquals("antwoord", oef.getAntwoord());
        Assert.assertEquals("feedbackPath", oef.getFeedback());
        Assert.assertEquals(doel, oef.getDoelstellingscodes());
        Assert.assertEquals(groeps, oef.getLijstGroepsbewerkingen());
        Assert.assertEquals(1, oef.getTijdsLimiet());
    }

    @Test(expected = IllegalArgumentException.class)
    public void aanmakenOefeningFoutieveGroepsbewerkingenNullTest(){

        Oefening oef = new Oefening("oefening3", "opgavePath", "antwoord", "feedbackPath",
                null,
                new ArrayList<Doelstellingscode>(),
                new Vak("wiskunde", "red"), 1);


    }
    @Test(expected = IllegalArgumentException.class)
    public void aanmakenOefeningFoutieveGroepsbewerkingenLeegTest(){

        Oefening oef = new Oefening("oefening3", "opgavePath", "antwoord", "feedbackPath",
                new ArrayList<Groepsbewerking>(),
                new ArrayList<Doelstellingscode>(),
                new Vak("wiskunde", "red"), 1);


    }

    @Test(expected = IllegalArgumentException.class)
    public void aanmakenOefeningFoutieveNaamNullTest(){

        Oefening oef = new Oefening(null, "opgavePath", "antwoord", "feedbackPath",
                new ArrayList<Groepsbewerking>(),
                new ArrayList<Doelstellingscode>(),
                new Vak("wiskunde", "red"), 1);


    }

    @Test(expected = IllegalArgumentException.class)
    public void aanmakenOefeningFoutieveNaamLeegTest(){

        Oefening oef = new Oefening("", "opgavePath", "antwoord", "feedbackPath",
                new ArrayList<Groepsbewerking>(),
                new ArrayList<Doelstellingscode>(),
                new Vak("wiskunde", "red"), 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void aanmakenOefeningFoutieveOpgaveLeegTest(){

        Oefening oef = new Oefening("oefening3", "", "antwoord", "feedbackPath",
                new ArrayList<Groepsbewerking>(),
                new ArrayList<Doelstellingscode>(),
                new Vak("wiskunde", "red"), 1);


    }

    @Test(expected = IllegalArgumentException.class)
    public void aanmakenOefeningFoutieveOpgaveNullTest(){

        Oefening oef = new Oefening("oefening3", null, "antwoord", "feedbackPath",
                new ArrayList<Groepsbewerking>(),
                new ArrayList<Doelstellingscode>(),
                new Vak("wiskunde", "red"), 1);


    }

    @Test(expected = IllegalArgumentException.class)
    public void aanmakenOefeningFoutieveAntwoordNullTest(){

        Oefening oef = new Oefening("oefening3", "opgavePath", null, "feedbackPath",
                new ArrayList<Groepsbewerking>(),
                new ArrayList<Doelstellingscode>(),
                new Vak("wiskunde", "red"), 1);


    }

    @Test(expected = IllegalArgumentException.class)
    public void aanmakenOefeningFoutieveAntwoordLeegTest(){

        Oefening oef = new Oefening("oefening3", "opgavePath", "", "feedbackPath",
                new ArrayList<Groepsbewerking>(),
                new ArrayList<Doelstellingscode>(),
                new Vak("wiskunde", "red"), 1);


    }

    @Test(expected = IllegalArgumentException.class)
    public void aanmakenOefeningFoutieveFeedbackLeegTest(){

        Oefening oef = new Oefening("oefening3", "opgavePath", "antwoord", "",
                new ArrayList<Groepsbewerking>(),
                new ArrayList<Doelstellingscode>(),
                new Vak("wiskunde", "red"), 1);


    }

    @Test(expected = IllegalArgumentException.class)
    public void aanmakenOefeningFoutieveFeedBackNullTest(){

        Oefening oef = new Oefening("oefening3", "opgavePath", "antwoord", null,
                new ArrayList<Groepsbewerking>(),
                new ArrayList<Doelstellingscode>(),
                new Vak("wiskunde", "red"), 1);


    }

    @Test(expected = IllegalArgumentException.class)
    public void aanmakenOefeningFoutieveVakNullTest(){

        Oefening oef = new Oefening("oefening3", "opgavePath", "antwoord", "feedbackPath",
                new ArrayList<Groepsbewerking>(),
                new ArrayList<Doelstellingscode>(),
                null,  1);


    }





}
