package domein;

import javax.persistence.Entity;

@Entity
public class GroepSpeelState extends GroepState{

    public GroepSpeelState(Groep groep) {
        super(groep);
    }

    public GroepSpeelState() {
    }

    public void blok() {

    }

    public void finish() {

    }
}
