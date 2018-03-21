package persistentie;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class Job {

    private ArrayBlockingQueue<List<String>> jobs = new ArrayBlockingQueue<>(20);

    public List<String> haalJob(){
        List<String> job = null;


        try {
            job = jobs.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return job;
    }

    public void plaatsJob(List<String> job)  {
        try {
            jobs.put(job);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
