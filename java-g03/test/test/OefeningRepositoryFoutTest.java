package test;


import domein.Oefening;
import domein.OefeningRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import persistentie.OefeningMapper;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RunWith(value=Parameterized.class)
public class OefeningRepositoryFoutTest {

    private OefeningRepository oefeningRepository;

    private OefeningMapper oefeningMapperDummy;

    private String oefeningNaam;
    private Oefening returnOef;
    private HashMap<String, Oefening> oefeningen;

    @Parameterized.Parameters
    public static Collection<Object[]> getTestParameters(){
        return Arrays.asList(
                new Object[][]{
                        //{"test", new Oefening("test", "4+4", "8", "Dit is een som", "wiskunde")},
                        //{"test", new Oefening("test", "Haal alle o's uit hoed", "hed", "mqlsjdfmlqksdjf", "taal")}


                }
        );
    }

    public OefeningRepositoryFoutTest(String oefeningNaam, Oefening returnOef, HashMap<String, Oefening> oefeningen){
        this.oefeningNaam = oefeningNaam;
        this.returnOef = returnOef;
        this.oefeningen = oefeningen;
    }

    @Before
    public void Before(){

        oefeningMapperDummy = Mockito.mock(OefeningMapper.class);

        oefeningRepository = new OefeningRepository(oefeningMapperDummy);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaakNieuwOefeningBestaatNaamFout(){
        Mockito.when(oefeningMapperDummy.zoekOefening(oefeningNaam))
                .thenReturn(returnOef);
        Mockito.when(oefeningMapperDummy.geefOefeningen())
                .thenReturn(this.oefeningen);
        oefeningRepository.voegOefeningToe("test", "4+4", "8", "Dit is een som");
        Mockito.verify(oefeningMapperDummy, Mockito.times(1)).geefOefeningen();
        Mockito.verify(oefeningMapperDummy, Mockito.times(1)).zoekOefening(oefeningNaam);
    }

}