//package test;
//
//
//import domein.Groepsbewerking;
//import domein.Oefening;
//import domein.OefeningBeheerder;
//import domein.Vak;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mockito;
//import persistentie.GenericDao;
//import persistentie.GenericDaoJpa;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class OefeningBeheerderTest {
//
//    private OefeningBeheerder oefeningBeheerder;
//
//    private GenericDao<Oefening> oefeningRepoDummy;
//
//    private String oefeningNaam;
//    private Oefening returnOef;
//    private List<Oefening> oefeningen;
//
//
//
//    @Before
//    public void before(){
//        oefeningRepoDummy = Mockito.mock(GenericDaoJpa.class);
//
//        List<Oefening> oefeningen = new ArrayList<>();
//        oefeningen.add(new Oefening("oefening3","opgavePath", "antwoord", "feedbackPath",new ArrayList<Groepsbewerking>(), new Vak("wiskunde", "red")));
//        oefeningen.add(new Oefening("oefening2","opgavePath", "antwoord", "feedbackPath",new ArrayList<Groepsbewerking>(), new Vak("wiskunde", "red")));
//        oefeningen.add(new Oefening("oefening1","opgavePath", "antwoord", "feedbackPath",new ArrayList<Groepsbewerking>(), new Vak("wiskunde", "red")));
//
//        Mockito.when(oefeningRepoDummy.findAll()).thenReturn(oefeningen);
//
//        oefeningBeheerder = new OefeningBeheerder();
//
//        oefeningBeheerder.setOefeningRepo(oefeningRepoDummy);
//    }
//
//    @Test
//    public void testOefeningVerwijderen(){ // TODO - nog kijken hoe void functie verwijderOefening via mockito werkt.
//        Oefening oef = new Oefening("oefening3","opgavePath", "antwoord", "feedbackPath",new ArrayList<Groepsbewerking>(), new Vak("wiskunde", "red"));
//
//        Assert.assertTrue(oefeningBeheerder.geefOefeningen().contains(oef));
//
//        List<Oefening> oefeningen = new ArrayList<>();
//        oefeningen.add(new Oefening("oefening2","opgavePath", "antwoord", "feedbackPath",new ArrayList<Groepsbewerking>(), new Vak("wiskunde", "red")));
//        oefeningen.add(new Oefening("oefening1","opgavePath", "antwoord", "feedbackPath",new ArrayList<Groepsbewerking>(), new Vak("wiskunde", "red")));
//
//        Mockito.when(oefeningRepoDummy.findAll()).thenReturn(oefeningen);
//
//        oefeningBeheerder.verwijderOefening();
//
//        Assert.assertFalse(oefeningBeheerder.geefOefeningen().contains(oef));
//    }
//
//    @Test
//    public void testOefeningVerwijderenOefeningBestaatNiet(){
//        Oefening oef = new Oefening("oefening3","opgavePath", "antwoord", "feedbackPath",new ArrayList<Groepsbewerking>(), new Vak("wiskunde", "red"));
//        Assert.assertTrue(oefeningBeheerder.geefOefeningen().contains(oef));
//
//        oefeningBeheerder.verwijderOefening();
//
//        Assert.assertTrue(oefeningBeheerder.geefOefeningen().contains(oef));
//    }
//
//    @Test
//    public void testOefeningWijzigen(){
//
//        /*oefeningBeheerder.wijzigOefening("oefening3", "oefening3", "veranderd", "antwoord", "feedbackPath", new ArrayList<Groepsbewerking>(), new Vak("Wiskunde","red"));
//
//        oefeningBeheerder.geefOefeningen();
//
//
//        Assert.assertTrue(oefeningBeheerder.geefOefeningen().contains(new Oefening("oefening3","opgavePath", "antwoord", "feedbackPath",new ArrayList<Groepsbewerking>(), new Vak("wiskunde", "red"))));
//   */ }
//
//}
