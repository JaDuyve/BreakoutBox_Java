package persistentie;

import domein.Oefening;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;


public class OefeningDaoJpa extends GenericDaoJpa<Oefening> implements OefeningDao {

    public OefeningDaoJpa(){
        super(Oefening.class);
    }

    @Override
    public Oefening getOefeningByName(String name) throws EntityNotFoundException {
        try {
            return em.createNamedQuery("Oefening.findByName", Oefening.class)
                    .setParameter("oefeningNaam", name)
                    .getSingleResult();

        }catch (NoResultException ex) {
            throw new EntityNotFoundException();
        }
    }
}
