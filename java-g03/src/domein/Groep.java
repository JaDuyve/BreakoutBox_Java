package domein;

import javax.persistence.*;
import java.util.*;

@Entity
public class Groep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    private String klas;
    private boolean contactLeer;
    private int progress;
    private int Fout;

    private String leerlingen;
    @OneToMany(cascade = CascadeType.PERSIST)
    private Map<Integer, Pad> paden;
//    @OneToOne(cascade=CascadeType.PERSIST)
//    private GroepState currentState;

    public Groep(String naam, String klas, List<String> leerlingen, Bob bob, boolean contactLeer) {
        setNaam(naam);
        setKlas(klas);
        setLeerlingen(leerlingCollectieOpvullen(leerlingen));
        setContactLeer(contactLeer);

        generatePaden(bob);

        progress = 1;
        Fout = 0;
    }

    protected Groep() {
    }

    private String leerlingCollectieOpvullen(List<String> leerlingen){
        StringBuilder namen = new StringBuilder();
        leerlingen.stream().forEach(l -> namen.append(l).append(", "));
        return namen.toString();
    }

    private void generatePaden(Bob bob) {
        List<Oefening> oefeningen = bob.getLijstOefeningen();
        List<Actie> acties = bob.getLijstActies();
        List<Pad> pads = new ArrayList<>();

        for (int i = 0; i < oefeningen.size() - 1; i++) {

            pads.add(new Pad(oefeningen.get(i), acties.get(i), isContactLeer()));
        }



        Collections.shuffle(pads);

        paden = new HashMap<>();

        for (int j = 0; j < pads.size(); j++) {
            paden.put(j + 1, pads.get(j));
        }

        paden.put(paden.size() + 1, new Pad(oefeningen.get(oefeningen.size() - 1), null, isContactLeer()));
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        if (naam.isEmpty() || naam.equals("")) {
            throw new IllegalArgumentException("Groepsaam mag niet leeg zijn");
        }
        this.naam = naam;
    }

    public String getKlas() {
        return klas;
    }

    public void setKlas(String klas) {
        if (klas.isEmpty() || klas.equals("")) {
            throw new IllegalArgumentException("Klas mag niet leeg zijn");
        }
        this.klas = klas;
    }

    public String getLeerlingen() {
        return leerlingen;
    }

    public void setLeerlingen(String leerlingen) {
        if (leerlingen.isEmpty()) {
            throw new IllegalArgumentException("Leerlingen mag niet leeg zijn");
        }
        this.leerlingen = leerlingen;
    }

    public Map<Integer, Pad> getPaden() {
        return paden;
    }

    public void setPaden(Map<Integer, Pad> paden) {
        if (paden.isEmpty()) {
            throw new IllegalArgumentException(("oefVolg mag niet leeg zijn"));
        }
        this.paden = paden;
    }

    public boolean isContactLeer() {
        return contactLeer;
    }

    public void setContactLeer(boolean contactLeer) {
        this.contactLeer = contactLeer;
    }

//    public GroepState getCurrentState() {
//        return currentState;
//    }

    @Override
    public String toString() {
        return getNaam().toLowerCase();
    }
}
