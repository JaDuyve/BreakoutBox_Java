package domein;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "NumerischeGroepsbewerkingOefening", schema = "project_prog_g03", catalog = "")
@IdClass(NumerischeGroepsbewerkingOefeningEntityPK.class)
public class NumerischeGroepsbewerkingOefeningEntity {
    private String oefeningNaam;
    private String groepsbewerkingNaam;

    @Id
    @Column(name = "oefeningNaam", nullable = false, length = 50)
    public String getOefeningNaam() {
        return oefeningNaam;
    }

    public void setOefeningNaam(String oefeningNaam) {
        this.oefeningNaam = oefeningNaam;
    }

    @Id
    @Column(name = "groepsbewerkingNaam", nullable = false, length = 50)
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
        NumerischeGroepsbewerkingOefeningEntity that = (NumerischeGroepsbewerkingOefeningEntity) o;
        return Objects.equals(oefeningNaam, that.oefeningNaam) &&
                Objects.equals(groepsbewerkingNaam, that.groepsbewerkingNaam);
    }

    @Override
    public int hashCode() {

        return Objects.hash(oefeningNaam, groepsbewerkingNaam);
    }
}
