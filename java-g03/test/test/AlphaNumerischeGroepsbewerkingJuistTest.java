package test;


import org.junit.Assert;
import org.junit.Test;


public class AlphaNumerischeGroepsbewerkingJuistTest {



    @Test
    public void aanmakenAlphaNumerischeGroepsbewerkingNaamSuccesTest(){
        Groepsbewerking groepsbewerking = new AlphaNumerischeGroepsbewerking("test", "Doe plus 4");
        Assert.assertEquals("test", groepsbewerking.getNaam());
        Assert.assertEquals("Doe plus 4", groepsbewerking.getOpgave());
    }


}