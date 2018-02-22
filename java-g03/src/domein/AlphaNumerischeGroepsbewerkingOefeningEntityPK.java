package domein;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class AlphaNumerischeGroepsbewerkingOefeningEntityPK implements Serializable {
    private String oefeningNaam;
    private String groepsBewerkingNaam;

    @Column(name = "oefeningNaam", nullable = false, length = 50)
    @Id
    public String getOefeningNaam() {
        return oefeningNaam;
    }

    public void setOefeningNaam(String oefeningNaam) {
        this.oefeningNaam = oefeningNaam;
    }

    @Column(name = "groepsBewerkingNaam", nullable = false, length = 50)
    @Id
    public String getGroepsBewerkingNaam() {
        return groepsBewerkingNaam;
    }

    public void setGroepsBewerkingNaam(String groepsBewerkingNaam) {
        this.groepsBewerkingNaam = groepsBewerkingNaam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlphaNumerischeGroepsbewerkingOefeningEntityPK that = (AlphaNumerischeGroepsbewerkingOefeningEntityPK) o;
        return Objects.equals(oefeningNaam, that.oefeningNaam) &&
                Objects.equals(groepsBewerkingNaam, that.groepsBewerkingNaam);
    }

    @Override
    public int hashCode() {

        return Objects.hash(oefeningNaam, groepsBewerkingNaam);
    }
}
