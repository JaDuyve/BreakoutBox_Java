package test;


import domein.AlphaNumerischeOefening;
import domein.NumerischeOefening;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value=Parameterized.class)
public class AlphaNumerischeOefeningFoutTest {

    private String naam;
    private String opgave;
    private String antwoord;
    private String feedback;
    private String vak;

    @Parameterized.Parameters
    public static Collection<String[]> getTestParameters(){
        return Arrays.asList(
                new String[][]{
                        {"test","","","",""},
                        {"test","Wat is 4 + 4","","",""}

                }
        );
    }

    public AlphaNumerischeOefeningFoutTest(String naam, String opgave, String antwoord, String feedback, String vak){
        this.naam = naam;
        this.opgave = opgave;
        this.antwoord = antwoord;
        this.feedback = feedback;
        this.vak = vak;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaakNumerischeOefeningFoutLang(){
        new AlphaNumerischeOefening(naam, opgave, antwoord, feedback, vak);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaakNumerischeOefeningFoutKort(){
        new AlphaNumerischeOefening(naam, opgave, vak, antwoord);
    }
}