package domein;

import javax.persistence.Entity;

@Entity
public class GroepGeblokkeerdState extends GroepState {

    public GroepGeblokkeerdState(Groep groep) {
        super(groep);
    }

    public GroepGeblokkeerdState() {
    }

    public void spelen(){

    }
}
