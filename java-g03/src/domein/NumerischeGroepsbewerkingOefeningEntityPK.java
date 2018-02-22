package domein;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class NumerischeGroepsbewerkingOefeningEntityPK implements Serializable {
    private String oefeningNaam;
    private String groepsbewerkingNaam;

    @Column(name = "oefeningNaam", nullable = false, length = 50)
    @Id
    public String getOefeningNaam() {
        return oefeningNaam;
    }

    public void setOefeningNaam(String oefeningNaam) {
        this.oefeningNaam = oefeningNaam;
    }

    @Column(name = "groepsbewerkingNaam", nullable = false, length = 50)
    @Id
    public String getGroepsbewerkingNaam() {
        return groepsbewerkingNaam;
    }

    public void setGroepsbewerkingNaam(String groepsbewerkingNaam) {
        this.groepsbewerkingNaam = groepsbewerkingNaam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumerischeGroepsbewerkingOefeningEntityPK that = (NumerischeGroepsbewerkingOefeningEntityPK) o;
        return Objects.equals(oefeningNaam, that.oefeningNaam) &&
                Objects.equals(groepsbewerkingNaam, that.groepsbewerkingNaam);
    }

    @Override
    public int hashCode() {

        return Objects.hash(oefeningNaam, groepsbewerkingNaam);
    }
}
