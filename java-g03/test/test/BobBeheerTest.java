package test;

import domein.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;
import persistentie.GenericDao;
import persistentie.GenericDaoJpa;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(value = Parameterized.class)
public class BobBeheerTest
{
    private BobBeheerder bobBeheerder;

    private GenericDao genericDaoDummy;
    private Bob bobTest;

    List<Bob> bobs = new ArrayList<>();

    @Parameters
    public static Collection<Object[]> getTestParameters(){
        return Arrays.asList(new Object[][]{
                {"bob1", new ArrayList<Oefening>(), new ArrayList<Actie>(), new ArrayList<Toegangscode>()},
                {"bob2", new ArrayList<Oefening>(), new ArrayList<Actie>(), new ArrayList<Toegangscode>()},
                {"bob3", new ArrayList<Oefening>(), new ArrayList<Actie>(), new ArrayList<Toegangscode>()}
        });
    }

    public BobBeheerTest(String naam, ArrayList<Oefening> oefeningen, ArrayList<Actie> acties, ArrayList<Toegangscode> toegangscodes)
    {
        this.bobTest = new Bob(naam, oefeningen, acties, toegangscodes);
    }

    @Before
    public void before(){

        genericDaoDummy = Mockito.mock(GenericDaoJpa.class);
        bobBeheerder = new BobBeheerder(genericDaoDummy);


        bobs.add(new Bob("bob1",new ArrayList<Oefening>(), new ArrayList<Actie>(), new ArrayList<Toegangscode>()));
        bobs.add(new Bob("bob2",new ArrayList<Oefening>(), new ArrayList<Actie>(), new ArrayList<Toegangscode>()));
        bobs.add(new Bob("bob3",new ArrayList<Oefening>(), new ArrayList<Actie>(), new ArrayList<Toegangscode>()));
    }

    @Test
    public void testBobVerwijderen(){


        Mockito.when(genericDaoDummy.findAll()).thenReturn(bobs);


        genericDaoDummy.insert(new Bob("bob1",new ArrayList<Oefening>(), new ArrayList<Actie>(), new ArrayList<Toegangscode>()));
        genericDaoDummy.insert(new Bob("bob2",new ArrayList<Oefening>(), new ArrayList<Actie>(), new ArrayList<Toegangscode>()));
        genericDaoDummy.insert(new Bob("bob3",new ArrayList<Oefening>(), new ArrayList<Actie>(), new ArrayList<Toegangscode>()));


        genericDaoDummy.delete(bobTest);

        Assert.assertFalse(bobBeheerder.geefBobs().contains(bobTest));
    }
}
