package test;


import domein.AlphaNumerischeGroepsbewerking;
import domein.Groepsbewerking;
import domein.NumerischeGroepsbewerking;
import org.junit.Assert;
import org.junit.Test;


public class NumerischeGroepsbewerkingJuistTest {



    @Test
    public void aanmakenNumerischeGroepsbewerkingNaamSuccesTest(){
        Groepsbewerking groepsbewerking = new NumerischeGroepsbewerking("test", "Doe plus 4");
        Assert.assertEquals("test", groepsbewerking.getNaam());
        Assert.assertEquals("Doe plus 4", groepsbewerking.getOpgave());
    }




}