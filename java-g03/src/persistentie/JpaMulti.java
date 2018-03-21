package persistentie;


import javax.persistence.EntityManager;
import java.util.List;

public class JpaMulti<T> implements Runnable {
    private JobJpa jobs;
    private final Class<T> type;
    private final EntityManager em;

    public JpaMulti(Class<T> type, EntityManager em, JobJpa jobs) {
        this.type = type;
        this.jobs = jobs;

        this.em = em;
    }

    @Override
    public void run() {
        List<Object> job;

        while (true) {

            job = jobs.haalJob();


            switch ((String) job.get(0)) {
                case "DELETE":
                    startTransaction();
                    delete((T) job.get(1));
                    commitTransaction();
                    break;
                case "INSERT":
                    startTransaction();
                    insert((T) job.get(1));
                    commitTransaction();
                    break;
                case "UPDATE":
                    startTransaction();
                    update((T) job.get(1));
                    commitTransaction();
                    break;
            }

        }
    }

    public  void startTransaction() {
        em.getTransaction().begin();
    }

    public void commitTransaction() {
        em.getTransaction().commit();
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
}
