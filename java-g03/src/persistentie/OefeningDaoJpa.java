package persistentie;

import domein.Oefening;

public class OefeningDaoJpa extends GenericDaoJpa<Oefening> implements OefeningDao {

    public OefeningDaoJpa() {
        super(Oefening.class);
    }


    @Override
    public int sitsInBob(String naam) {
        /*TypedQuery<Oefening> q = em.createNamedQuery("Oefening.sitsInBob", Oefening.class);

        return  q.getSingleResult();*/
       return 0;

    }
}
