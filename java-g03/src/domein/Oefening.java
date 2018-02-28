package domein;

import javax.persistence.*;
import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Oefening {


    @Id
    private String naam;
    private String opgave;
    private String feedback;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Groepsbewerking> lijstGroepsbewerkingen;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Vak vak;

    public String getNaam() {
        return this.naam;
    }

    public void setNaam(String naam) {
        if (naam == null || naam.equals("")) {
            throw new IllegalArgumentException("Naam mag niet leeg zijn.");
        }
        this.naam = naam;
    }

    public String getOpgave() {
        return this.opgave;
    }

    public void setOpgave(String opgave) {

        if (opgave == null || opgave.equals("")) {
            throw new IllegalArgumentException("Opgave mag niet leeg gelaten worden");
        }
        this.opgave = opgave;
    }

    public void setVak(Vak vak) {
        this.vak = vak;
    }

    public Vak getVak() {
        return vak;
    }

    public String getFeedback() {
        return this.feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Collection<Groepsbewerking> getLijstGroepsbewerkingen() {
        return lijstGroepsbewerkingen;
    }

    public void setLijstGroepsbewerkingen(List<Groepsbewerking> lijstGroepsbewerkingen) {
        this.lijstGroepsbewerkingen = lijstGroepsbewerkingen;
    }

    public Oefening() {
    }



    /**
     * @param naam
     * @param opgave
     * @param feedback
     * @param vak
     */
    public Oefening(String naam, String opgave, String feedback, List<Groepsbewerking> groepsbewerkingen ,Vak vak) {
        setNaam(naam);
        setOpgave(opgave);
        setFeedback(feedback);
        setLijstGroepsbewerkingen(groepsbewerkingen);
        setVak(vak);
    }

    /**
     * @param naam
     * @param opgave
     * @param vak
     */
    public Oefening(String naam, String opgave, List<Groepsbewerking> groepsbewerkingen, Vak vak) {

        setNaam(naam);
        setOpgave(opgave);
        setLijstGroepsbewerkingen(groepsbewerkingen);
        setVak(vak);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Oefening)) return false;
        Oefening oefening = (Oefening) o;
        return Objects.equals(getNaam(), oefening.getNaam());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getNaam());
    }
}