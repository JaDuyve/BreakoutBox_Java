package test;


import domein.Oefening;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value=Parameterized.class)
public class OefeningFoutTest {

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

    public OefeningFoutTest(String naam, String opgave, String antwoord, String feedback, String vak){
        this.naam = naam;
        this.opgave = opgave;
        this.antwoord = antwoord;
        this.feedback = feedback;
        this.vak = vak;
    }

    @Test
    public void testMaakOefeningFoutLang(){
        new Oefening(naam, opgave, antwoord, feedback, vak);
    }

    @Test
    public void testMaakOefeningFoutKort(){
        new Oefening(naam, opgave, antwoord, vak);
    }
}