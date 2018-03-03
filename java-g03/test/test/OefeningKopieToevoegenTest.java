package test;

import domein.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import persistentie.OefeningDao;

import java.util.ArrayList;
import java.util.List;

public class OefeningKopieToevoegenTest
{
    private OefeningDao oefeningRepo;
    private OefeningBeheerder oefeningBeheerder;

    @Before
    public void before()
    {
        oefeningRepo = Mockito.mock(OefeningDao.class);
        oefeningBeheerder = new OefeningBeheerder(oefeningRepo);
    }

    @Test
    public void oefeningKopieren()
    {
        List<Groepsbewerking> test = new ArrayList<Groepsbewerking>();
        test.add(new Groepsbewerking("test", "test"));
        Oefening oef = new Oefening("test", "test", "test", test, new Vak("wiskunde", "blauw")) ;

        Mockito.when(oefeningRepo.getOefeningByName(oef.getNaam())).thenReturn(oef);


    }
}
