package domein;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class SessieBeheerder {
    private Comparator<Sessie> bySessieNaam;
    private Sessie sessie;
    private List<Sessie> sessies;

    public SessieBeheerder() {
        sessies = new ArrayList<>();

    }

    public void create(String naam, LocalDate startDate, Bob bob, File groepen, boolean contactLeer){
        sessie = new Sessie(naam, startDate, bob, groepen, contactLeer);
        sessies.add(sessie);
    }

    public void changeFilter(String naam){
        throw new NotImplementedException();
    }
}
