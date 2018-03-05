package persistentie;

import domein.Oefening;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

public class OefeningDaoJpa extends GenericDaoJpa<Oefening> implements OefeningDao {

    public OefeningDaoJpa() {
        super(Oefening.class);
    }



    @Override
    public void deleteOefeningByName(String name) {
        em.createNamedQuery("Oefening.deleteByName", Oefening.class)
                .setParameter("oefeningNaam", name);


    }

    @Override
    public Oefening getOefeningByName(String name) {
      return  em.createNamedQuery("Oefening.getByName", Oefening.class).setParameter("oefeningNaam", name).getSingleResult();
    }
}
