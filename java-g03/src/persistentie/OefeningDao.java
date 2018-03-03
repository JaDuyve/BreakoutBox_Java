package persistentie;

import domein.Oefening;

import javax.persistence.EntityNotFoundException;

public interface OefeningDao extends GenericDao<Oefening> {
    public void deleteOefeningByName(String name);
    public Oefening getOefeningByName(String name);
}
