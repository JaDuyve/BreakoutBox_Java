package domein;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class GroepState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade = CascadeType.PERSIST)
    protected Groep groep;

    public GroepState(Groep groep) {

    }

    protected GroepState() {

    }

    public void finish() {

    }

    public void blok() {

    }

    public void kanSpelen() {

    }

    public void spelen() {

    }
}
