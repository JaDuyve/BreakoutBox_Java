package domein;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
public class AlphaNumerischeOefening extends Oefening
{
    private String antwoord;

    protected  AlphaNumerischeOefening()
    {

    }

    public AlphaNumerischeOefening(String naam, String opgave, String feedback, String vak, String antwoord)
{
    super(naam, opgave, feedback, vak);
    this.antwoord = antwoord;
}

    public AlphaNumerischeOefening(String naam, String opgave, String vak, String antwoord)
    {
        super(naam, opgave, vak);
        this.antwoord = antwoord;
    }

    public String getAntwoord() {
        return antwoord;
    }

    public void setAntwoord(String antwoord)
    {
        if(antwoord == null || antwoord.equals(""))
        {
            throw new IllegalArgumentException("antwoord moet ingevuld zijn");
        }
        this.antwoord = antwoord;
    }


}
