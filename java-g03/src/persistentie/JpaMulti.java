package persistentie;


import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class JpaMulti<T> implements Runnable, GenericDao<T>{
    private static final EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
    protected static final EntityManager em = emf.createEntityManager();

    private JobJpa jobs;
    private final Class<T> type;

    public JpaMulti(Class<T> type,  JobJpa jobs) {
        this.type = type;
        this.jobs = jobs;

    }

    @Override
    public void run() {
        List<Object> job;

        while (true) {

            job = jobs.haalJob();

            try {
                switch ((String) job.get(0)) {
                    case "DELETE":
                        JpaMulti.startTransaction();
                        delete((T) job.get(1));
                        JpaMulti.commitTransaction();
                        break;
                    case "INSERT":
                        JpaMulti.startTransaction();
                        insert((T) job.get(1));
                        JpaMulti.commitTransaction();
                        break;
                    case "UPDATE":
                        JpaMulti.startTransaction();
                        update((T) job.get(1));
                        JpaMulti.commitTransaction();
                        break;
                }
            }catch(Exception e){
                JpaMulti.rollbackTransaction();
            }


        }
    }

    private  static void startTransaction() {
        em.getTransaction().begin();
    }

    private static void commitTransaction() {
        em.getTransaction().commit();
    }

    private static void rollbackTransaction() {
        em.getTransaction().rollback();
    }


    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public <U> T get(U id) {
        return null;
    }

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
        return false;
    }
}
