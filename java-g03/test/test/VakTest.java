package test;


import domein.Vak;
import org.junit.Assert;
import org.junit.Test;


public class VakTest {

    @Test
    public void aanmakenVakNaamSuccesTest(){
        Vak vak = new Vak("Wiskunde", "red");
        Assert.assertEquals("Wiskunde", vak.getNaam());
    }

    @Test(expected = IllegalArgumentException.class)
    public void aanmakenVakNaamLegeTekstFoutTest(){
        new Vak("","");
    }

    @Test(expected = IllegalArgumentException.class)
    public void aanmakenVakNaamNullFoutTest(){
        new Vak(null, null);
    }
}