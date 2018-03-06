package test;

import domein.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BobTest {
    private List<Oefening> oefeningen;
    private List<Actie> acties;
    private List<Toegangscode> codes;
    private Vak vak;
    @Before
    public void before() {
        oefeningen = new ArrayList<>();
        acties = new ArrayList<>();
        codes = new ArrayList<>();
        vak = new Vak("Wiskunde", "rood");
    }

    @Test
    public void aanmakenBobNaamSuccesTest() {
        Bob bob = new Bob("Bobbybob", oefeningen, acties, codes, vak);
        Assert.assertEquals("Bobbybob", bob.getNaam());
        Assert.assertEquals(new Vak("Wiskunde", "rood"), bob.getVak());
        Assert.assertEquals(new ArrayList<Oefening>(), bob.getLijstOefeningen());
        Assert.assertEquals(new ArrayList<Actie>(), bob.getLijstActies());
        Assert.assertEquals(new ArrayList<Toegangscode>(), bob.getLijstToegangscode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void aanmakenBobAllesLeegTest() {
        //new Bob("",);
    }

    @Test(expected = IllegalArgumentException.class)
    public void aanmakenBobNaamNullFoutTest() {
        new Vak(null, null);
    }


}