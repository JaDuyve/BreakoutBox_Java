package persistentie;

import domein.Oefening;

public interface OefeningDao extends GenericDao<Oefening> {
    public int sitsInBob(String naam);
}
