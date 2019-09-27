import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Tariq
 * Handles application Threading
 */


public class ServerChat {

    //The amount of clients/threads we are limiting to.
    static final int MAX = 4;

    public static void main(String[] args) {


        Runnable runnable1 = new ThreadChat("Thread1", 9000);
        Runnable runnable2 = new ThreadChat("Thread2", 9001);
        Runnable runnable3 = new ThreadChat("Thread3", 9002);
        Runnable runnable4 = new ThreadChat("Thread4", 9003);


        //Creates a threadpool with the maximum numbers of clients allowed.
        ExecutorService executorService = Executors.newFixedThreadPool(MAX);

        executorService.execute(runnable1);
        executorService.execute(runnable2);
        executorService.execute(runnable3);
        executorService.execute(runnable4);





    }



}
