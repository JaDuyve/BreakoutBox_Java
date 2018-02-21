package domein;

import java.util.*;

public abstract class Oefening {

    private Vak vak;
    private Collection<Groepsbewerking> groepsbewerkingen;
    private String naam;
    private String opgave;
    private String feedback;

    public String getNaam() {
        return this.naam;
    }

    public void setNaam(String naam) {
        if (naam == null || naam.equals("")) {
            throw new IllegalArgumentException("Naam mag niet leeg zijn");
        }
        this.naam = naam;
    }

    public String getOpgave() {
        return this.opgave;
    }

    public void setOpgave(String opgave) {
        if (opgave == null || opgave.equals("")) {
            throw new IllegalArgumentException("Opgave mag niet leeg zijn");
        }
        this.opgave = opgave;
    }


    public String getFeedback() {
        return this.feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setGroepsbewerkingen(Collection<Groepsbewerking> groepsbewerkingen) {
        this.groepsbewerkingen = groepsbewerkingen;
    }

    public void setVak(Vak vak) {
        this.vak = vak;
    }

    /**
     * @param naam
     * @param opgave
     * @param feedback
     * @param vak
     */
    public Oefening(String naam, String opgave, String feedback, String vak) {


        setNaam(naam);
        setOpgave(opgave);
        setFeedback(feedback);
        setVak(new Vak(vak));
        setGroepsbewerkingen(new ArrayList<Groepsbewerking>());
    }

    /**
     * @param naam
     * @param opgave
     * @param vak
     */
    public Oefening(String naam, String opgave, String vak) {
        this(naam, opgave,  null, vak);
    }

    /**
     * @param groepsbewerking
     */
    public void GroepsbewerkingToevoegen(Groepsbewerking groepsbewerking) {
        this.groepsbewerkingen.add(groepsbewerking);
    }

    /**
     * @param groepsbewerking
     */
    public void GroepsbewerkingVerwijderen(Groepsbewerking groepsbewerking) {
        this.groepsbewerkingen.remove(groepsbewerking);
    }

}