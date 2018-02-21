package domein;

import com.sun.xml.internal.ws.api.pipe.ClientPipeAssemblerContext;

import javax.persistence.*;
import java.util.*;

@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class Oefening {

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Vak vak;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Collection<Groepsbewerking> groepsbewerkingen;
    @Id
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

    protected  Oefening()
    {

    }

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