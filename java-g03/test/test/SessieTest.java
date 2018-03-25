package test;

import domein.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SessieTest {

    private Bob bob;
    private List<Oefening> oefeningen;
    private List<Groepsbewerking> groepsbewerkingen;
    private List<Actie> acties;
    private Vak vak;
    private List<Doelstellingscode> doelstellingen;

    @Before
    public void before() {
        vak = new Vak("Wiskunde", "Rood");
        oefeningen = new ArrayList<>();
        acties = new ArrayList<>();
        acties.add(new Actie("actie1", "opgave1"));
        groepsbewerkingen = new ArrayList<>();
        groepsbewerkingen.add(new Groepsbewerking("berwerkingNaam1", "opgavePath", "5", Bewerking.AFTREKKING));
        groepsbewerkingen.add(new Groepsbewerking("berwerkingNaam2", "opgavePath2", "7", Bewerking.VERMENIGVULDIGING));
        doelstellingen = new ArrayList<>();
        doelstellingen.add(new Doelstellingscode("es54t"));
        doelstellingen.add(new Doelstellingscode("4s5f4"));
        oefeningen.add(new Oefening("TestOefening", "OefeningOpgave", "35", "feedbackPath", groepsbewerkingen, doelstellingen, vak, 5));
        oefeningen.add(new Oefening("TestOefening2", "OefeningOpgave", "46", "feedbackPath", groepsbewerkingen, doelstellingen, vak, 5));
        bob = new Bob("TestBob", oefeningen,  acties);
    }



    @Test
    public void startDatumJuistTest(){
        Sessie sessie = new Sessie("Test", new Date(2018, 05, 30), bob, new File("C:\\Users\\Jari Duyvejonck\\Documents\\Groepen2MetData.xlsx"), false);
        Assert.assertEquals(new Date(2018, 05, 30).toString(), sessie.getStartDatum().toString());
    }

    @Test (expected = IllegalArgumentException.class)
    public void startDatumTeVroegWerptException() {
        Sessie sessie = new Sessie("Test", new Date(2018, 03, 23), bob, new File("C:\\Users\\Jari Duyvejonck\\Documents\\Groepen2MetData.xlsx"), false);
    }

    @Test (expected = IllegalArgumentException.class)
    public void startDatumVerkeerdeInvoerWerptException(){
        Sessie sessie = new Sessie("Test", new Date(2046517, 025, 40), bob, new File("C:\\Users\\Jari Duyvejonck\\Documents\\Groepen2MetData.xlsx"), false);
    }
}
