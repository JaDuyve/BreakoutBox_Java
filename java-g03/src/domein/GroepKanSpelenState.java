package domein;

import javax.persistence.Entity;

@Entity
public class GroepKanSpelenState extends GroepState{

    public GroepKanSpelenState(Groep groep) {
        super(groep);
    }

    public GroepKanSpelenState() {
    }

    public void spelen(){

    }
}
