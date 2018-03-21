package domein;

import javax.persistence.*;
import java.security.SecureRandom;
import java.util.List;

@Entity
public class Pad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean contactLeer;
    private String antwoord;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Actie actie;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Oefening oefening;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Groepsbewerking groepsbewerking;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Toegangscode toegangscode;

    @Transient
    private static SecureRandom random = new SecureRandom();

    public Pad(Oefening oefening, Actie actie, boolean contactLeer) {
        setContactLeer(contactLeer);
        setActie(actie);
        setOefening(oefening);

        setToegangscode(new Toegangscode(random.nextInt(10000)));

        List<Groepsbewerking> groepsbewerkingList = oefening.getLijstGroepsbewerkingen();
        setGroepsbewerking(groepsbewerkingList.get(random.nextInt(groepsbewerkingList.size() - 1)));

        double oefAntwoord = Double.parseDouble(getOefening().getAntwoord());
        double groepWaarde = Double.parseDouble(getGroepsbewerking().getWaarde());

        switch (getGroepsbewerking().getBewerking()){
            case VERMENIGVULDIGING:
                setAntwoord(Double.toString(oefAntwoord * groepWaarde));
                break;
            case DELING:
                setAntwoord(Double.toString(oefAntwoord / groepWaarde));
                break;
            case OPTELLING:
                setAntwoord(Double.toString(oefAntwoord + groepWaarde));
                break;
            case AFTREKKING:
                setAntwoord(Double.toString(oefAntwoord - groepWaarde));
                break;
        }

    }

    protected Pad() {

    }

    public boolean isContactLeer() {
        return contactLeer;
    }

    public void setContactLeer(boolean contactLeer) {

        this.contactLeer = contactLeer;
    }

    public String getAntwoord() {
        return antwoord;
    }

    public void setAntwoord(String antwoord) {
        this.antwoord = antwoord;
    }

    public Actie getActie() {

        return actie;
    }

    public void setActie(Actie actie) {

        this.actie = actie;
    }

    public Oefening getOefening() {

        return oefening;
    }

    public void setOefening(Oefening oefening) {
        if (oefening == null) {
            throw new IllegalArgumentException("Oefening mag niet null zijn bij een pad");
        }
        this.oefening = oefening;
    }

    public Groepsbewerking getGroepsbewerking() {
        return groepsbewerking;
    }

    public void setGroepsbewerking(Groepsbewerking groepsbewerking) {
        this.groepsbewerking = groepsbewerking;
    }

    public Toegangscode getToegangscode() {
        return toegangscode;
    }

    public void setToegangscode(Toegangscode toegangscode) {
        this.toegangscode = toegangscode;
    }


}
