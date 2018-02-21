package test;


import domein.AlphaNumerischeGroepsbewerking;
import domein.Groepsbewerking;
import domein.Vak;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


public class AlphaNumerischeGroepsbewerkingJuistTest {



    @Test
    public void aanmakenAlphaNumerischeGroepsbewerkingNaamSuccesTest(){
        Groepsbewerking groepsbewerking = new AlphaNumerischeGroepsbewerking("test", "Doe plus 4");
        Assert.assertEquals("test", groepsbewerking.getNaam());
        Assert.assertEquals("Doe plus 4", groepsbewerking.getOpgave());
    }


}