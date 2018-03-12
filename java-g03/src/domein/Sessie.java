package domein;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

public class Sessie {
    private String naam;
    private Date startDatum;
    private int code;
    private boolean contactLeer;
    private static SecureRandom random = new SecureRandom();
    private List<Groep> groepen;
    private Bob bob;

    public Sessie(String naam, Date startDatum,Bob bob, File groepen, boolean contactLeer) {
        setNaam(naam);
        setStartDatum(startDatum);
        setBob(bob);
        groepenToevoegen(groepen);
        setContactLeer(contactLeer);
        setCode(random.nextInt(10000)+ 1000);
    }

    private void groepenToevoegen(File groepen){
        throw new NotImplementedException();
    }

    public Bob getBob() {
        return bob;
    }

    public void setBob(Bob bob) {
        if (bob == null){
            throw new IllegalArgumentException("Bob mag niet leeg zijn");
        }
        this.bob = bob;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        if (naam.isEmpty() || naam.equals("")){
            throw new IllegalArgumentException("naam mag niet leeg zijn");
        }
        this.naam = naam;
    }

    public Date getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(Date startDatum) {
        if (startDatum == null){
            throw new IllegalArgumentException("startDatum mag niet leeg zijn");
        }
        this.startDatum = startDatum;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isContactLeer() {
        return contactLeer;
    }

    public void setContactLeer(boolean contactLeer) {
        this.contactLeer = contactLeer;
    }
}
