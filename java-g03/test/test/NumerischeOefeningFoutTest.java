package test;


import domein.NumerischeOefening;
import domein.Oefening;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value=Parameterized.class)
public class NumerischeOefeningFoutTest {

    private String naam;
    private String opgave;
    private double antwoord;
    private String feedback;
    private String vak;

    @Parameterized.Parameters
    public static Collection<Object[]> getTestParameters(){
        return Arrays.asList(
                new Object[][]{
                        {"test","",3,"",""},
                        {"test","Wat is 4 + 4","","",""}

                }
        );
    }

    public NumerischeOefeningFoutTest(String naam, String opgave, double antwoord, String feedback, String vak){
        this.naam = naam;
        this.opgave = opgave;
        this.antwoord = antwoord;
        this.feedback = feedback;
        this.vak = vak;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaakNumerischeOefeningFoutLang(){
        new NumerischeOefening(naam, opgave, antwoord, feedback, vak);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaakNumerischeOefeningFoutKort(){
        new NumerischeOefening(naam, opgave, vak, antwoord);
    }
}