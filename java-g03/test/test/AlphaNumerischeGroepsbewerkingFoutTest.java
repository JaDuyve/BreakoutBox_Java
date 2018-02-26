package test;


import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


public class AlphaNumerischeGroepsbewerkingFoutTest {

    private String naam;
    private String opgave;


    @Parameterized.Parameters
    public static Collection<String[]> getTestParameters(){
        return Arrays.asList(
                new String[][]{
                        {"", ""},
                        {null, ""},
                        {"", null},
                        {"test", null},
                        {null,"Doe plus 4"},
                        {"", "Doe plus 4"},
                        {"test", ""}
                }
        );
    }

    public AlphaNumerischeGroepsbewerkingFoutTest(String naam, String opgave){
        this.naam = naam;
        this.opgave = opgave;
    }


    @Test(expected = IllegalArgumentException.class)
    public void aanmakenVakNaamNullFoutTest(){
        new AlphaNumerischeGroepsbewerking(naam, opgave);
    }
}