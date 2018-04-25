package domein;

import javax.persistence.Entity;

@Entity
public class GroepFinishedState extends GroepState{

    public GroepFinishedState(Groep groep) {
        super(groep);
    }

    public GroepFinishedState() {

    }

}
