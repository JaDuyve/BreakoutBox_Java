package persistentie;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class JobJpa {

    private ArrayBlockingQueue<List<Object>> jobs = new ArrayBlockingQueue<>(20);

    public List<Object> haalJob(){
        List<Object> job = null;

        try {
            job = jobs.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return job;
    }

    public void plaatsJob(List<Object> job)  {
        try {
            jobs.put(job);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
