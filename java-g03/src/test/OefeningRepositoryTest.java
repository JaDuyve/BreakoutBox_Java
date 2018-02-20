package test;


import domein.Oefening;
import domein.OefeningRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import persistentie.OefeningMapper;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value=Parameterized.class)
public class OefeningRepositoryTest {

    private OefeningRepository oefeningRepository;

    private OefeningMapper oefeningMapperDummy;

    private String oefeningNaam;
    private Oefening returnOef;

    @Parameterized.Parameters
    public static Collection<String[]> getTestParameters(){
        return Arrays.asList(
                new String[][]{
                        {"test", null}
                }
        );
    }

    public OefeningRepositoryTest(String oefeningNaam, Oefening returnOef){
        this.oefeningNaam = oefeningNaam;
        this.returnOef = returnOef;
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
        Oefening foutOef = new Oefening("test", "4+4", "8", "Dit is een som", "wiskunde");
        oefeningRepository.voegOefeningToe(foutOef);

    }

}