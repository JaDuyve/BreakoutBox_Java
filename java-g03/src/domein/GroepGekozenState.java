package domein;

import javax.persistence.Entity;

@Entity
public class GroepGekozenState extends GroepState{
    public GroepGekozenState(Groep groep) {
        super(groep);
    }

    public GroepGekozenState() {

    }

    public void kanSpelen(){

    }
}
