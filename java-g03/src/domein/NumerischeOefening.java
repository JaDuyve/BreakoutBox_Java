package domein;

public class NumerischeOefening extends Oefening
{

    private double antwoord;

    public NumerischeOefening(String naam, String opgave, String antwoord, String feedback, String vak)
    {
        super(naam, opgave, feedback, vak);
    }

    public NumerischeOefening(String naam, String opgave, String vak, double antwoord)
    {
        super(naam, opgave, vak);
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
