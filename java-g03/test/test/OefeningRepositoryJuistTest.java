package test;


import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import persistentie.OefeningMapper;

import java.util.*;

@RunWith(value=Parameterized.class)
public class OefeningRepositoryJuistTest {

    private OefeningRepository oefeningRepository;

    private OefeningMapper oefeningMapperDummy;

    private String oefeningNaam;
    private Oefening returnOef;
    private List<Oefening> oefeningen;
    private List<Oefening> resultOefeningen;
    @Parameterized.Parameters
    public static Collection<Object[]> getTestParameters(){
        HashMap<String, Oefening> resultoef1 = new HashMap<String, Oefening>();
        //resultoef1.put("testje", new Oefening("testje", "4+4", "8", "Dit is een som", "wiskunde"));
        return Arrays.asList(
                new Object[][]{
                        {"test", null, new HashMap<String, Oefening> ()},
                        {"test", null, resultoef1}

                }
        );
    }

    public OefeningRepositoryJuistTest(String oefeningNaam, Oefening returnOef, List<Oefening> oefeningen, List<Oefening> resultOefeningen){
        this.oefeningNaam = oefeningNaam;
        this.returnOef = returnOef;
        this.oefeningen = oefeningen;
        this.resultOefeningen = resultOefeningen;
    }

    @Before
    public void Before(){

        oefeningMapperDummy = Mockito.mock(OefeningMapper.class);

        oefeningRepository = new OefeningRepository(oefeningMapperDummy);
    }

    @Test
    public void testMaakNieuwOefeningBestaatNaamFout(){
        Mockito.when(oefeningMapperDummy.zoekOefening(oefeningNaam))
                .thenReturn(returnOef);
        Mockito.when(oefeningMapperDummy.geefOefeningen())
                .thenReturn(this.oefeningen);
        oefeningRepository.voegOefeningToe("test", "4+4", "8", "Dit is een som");
        Mockito.verify(oefeningMapperDummy, Mockito.times(1)).geefOefeningen();
        Mockito.verify(oefeningMapperDummy, Mockito.times(1)).zoekOefening(oefeningNaam);
        Assert.assertEquals(resultOefeningen, oefeningRepository.geefOefeningen());
    }

}