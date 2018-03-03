package test;

import domein.Groepsbewerking;
import domein.Oefening;
import domein.OefeningBeheerder;
import domein.Vak;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import persistentie.OefeningDao;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

        assertFalse(oefeningBeheerder.geefOefeningen().contains(oef));
        oefeningBeheerder.createOefening(oef.getNaam(), oef.getOpgave(), oef.getAntwoord(), oef.getFeedback() ,oef.getLijstGroepsbewerkingen(), oef.getVak());
        assertTrue(oefeningBeheerder.geefOefeningen().contains(oef));
        oefeningBeheerder.kopieOefening(oef.getNaam()); // TODO - Thibaut waarom is dit nodig? (De functie kopeiOefening)

    }
}
