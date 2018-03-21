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
                    delete((T) job.get(1));
                    break;
                case "INSERT":
                    insert((T) job.get(1));
                    break;
                case "UPDATE":
                    update((T) job.get(1));
                    break;
            }

        }
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
