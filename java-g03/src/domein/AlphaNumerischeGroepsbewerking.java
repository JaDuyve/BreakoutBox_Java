package domein;

import javax.persistence.Entity;

@Entity
public class AlphaNumerischeGroepsbewerking extends Groepsbewerking
{

    protected AlphaNumerischeGroepsbewerking(){}

    public AlphaNumerischeGroepsbewerking(String naam, String opgave)
    {
        super(naam, opgave);
    }
}
