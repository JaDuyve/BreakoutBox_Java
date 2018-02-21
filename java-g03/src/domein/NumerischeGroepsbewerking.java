package domein;

import javax.persistence.Entity;

@Entity
public class NumerischeGroepsbewerking extends Groepsbewerking
{

    protected NumerischeGroepsbewerking(){}

    public NumerischeGroepsbewerking(String naam, String opgave)
    {
        super(naam, opgave);
    }
}
