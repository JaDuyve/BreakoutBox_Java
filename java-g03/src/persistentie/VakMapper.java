package persistentie;

import domein.Vak;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.Map;

public class VakMapper {

    public VakMapper() {

    }

    public Map<String, Vak> geefVakken(){
        /*Map<String, Vak> map = new HashMap<>();

        EntityManager e = JPAUtil.getEntityManagerFactory().createEntityManager();

        TypedQuery<Vak> findAll = e.createNamedQuery("Vak.findAll", Vak.class);
        return map;*/
        throw new UnsupportedOperationException();

    }
}
