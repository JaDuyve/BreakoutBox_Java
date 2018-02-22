package domein;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "AlphaNumerischeGroepsbewerkingOefening", schema = "project_prog_g03", catalog = "")
@IdClass(AlphaNumerischeGroepsbewerkingOefeningEntityPK.class)
public class AlphaNumerischeGroepsbewerkingOefeningEntity {
    private String oefeningNaam;
    private String groepsBewerkingNaam;

    @Id
    @Column(name = "oefeningNaam", nullable = false, length = 50)
    public String getOefeningNaam() {
        return oefeningNaam;
    }

    public void setOefeningNaam(String oefeningNaam) {
        this.oefeningNaam = oefeningNaam;
    }

    @Id
    @Column(name = "groepsBewerkingNaam", nullable = false, length = 50)
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
        AlphaNumerischeGroepsbewerkingOefeningEntity that = (AlphaNumerischeGroepsbewerkingOefeningEntity) o;
        return Objects.equals(oefeningNaam, that.oefeningNaam) &&
                Objects.equals(groepsBewerkingNaam, that.groepsBewerkingNaam);
    }

    @Override
    public int hashCode() {

        return Objects.hash(oefeningNaam, groepsBewerkingNaam);
    }
}
