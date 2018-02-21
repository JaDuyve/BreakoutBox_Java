package domein;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class NumerischeOefening extends Oefening
{

    private double antwoord;

    protected NumerischeOefening()
    {

    }

    public NumerischeOefening(String naam, String opgave, double antwoord, String feedback, String vak)
    {
        super(naam, opgave, feedback, vak);
        setAntwoord(antwoord);
    }

    public NumerischeOefening(String naam, String opgave, String vak, double antwoord)
    {
        super(naam, opgave, null, vak);
        setAntwoord(antwoord);
    }



    public double getAntwoord() {
        return antwoord;
    }

    public void setAntwoord(double antwoord)
    {
        this.antwoord = antwoord;
    }

}
