package persistentie;

import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericDaoJpa<T> implements GenericDao<T> {
    private static final EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
    protected static final EntityManager em = emf.createEntityManager();
    private final Class<T> type;
//    private JobJpa jobs;
//    JpaMulti<T> multi;

    public GenericDaoJpa(Class<T> type) {
        this.type = type;
//        this.jobs = new JobJpa();
//        multi = new JpaMulti<>(this.type, this.jobs);
//        new Thread(multi).start();
    }

    public static void closePersistency() {
        em.close();
        emf.close();
    }

    public static void startTransaction() {
        em.getTransaction().begin();
    }

    public static void commitTransaction() {
        em.getTransaction().commit();
    }

    public static void rollbackTransaction() {
        em.getTransaction().rollback();
    }

    @Override
    public List<T> findAll() {
        //return em.createNamedQuery(type.getName()+".findAll", type).getResultList();
        return em.createQuery("select entity from " + type.getName() + " entity", type).getResultList();
    }

    @Override
    public <U> T get(U id) {
        T entity = em.find(type, id);
        return entity;
    }

//    public void update(T object) {
//        jobs.plaatsJob(new ArrayList<>(Arrays.asList("UPDATE", object)));
//    }
//
//    public void delete(T object) {
//        jobs.plaatsJob(new ArrayList<>(Arrays.asList("DELETE", object)));
//    }
//
//    public void insert(T object) {
//        jobs.plaatsJob(new ArrayList<>(Arrays.asList("INSERT", object)));
//    }

    public void update(T object) {
        em.merge(object);
    }

    public void delete(T object) {
        em.remove(em.merge(object));
    }

    public void insert(T object) {
        em.persist(object);
    }

    @Override
    public <U> boolean exists(U id) {
        T entity = em.find(type, id);
        return entity != null;
    }


}
