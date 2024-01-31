
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        // declare the number of philosophers in this simulation
        int numPhilosophers = 5;

        // making an object of ExecutorService to manage the threads of philosophers
        ExecutorService executorService = Executors.newFixedThreadPool(numPhilosophers);

        // an array of Fork objects
        Fork[] forks = new Fork[numPhilosophers];

        // initialize forks
        for(int i = 0; i < numPhilosophers; i++){
            forks[i] = new Fork();
        }

        // an array of philosophers
        Philosopher[] philosophers = new Philosopher[numPhilosophers];

        // initialize philosophers
        for(int i = 0; i < numPhilosophers; i++){
            philosophers[i] = new Philosopher(i+1,forks[i], forks[(i+1)%numPhilosophers],i +1);
        }


        // create the threads for each philosopher -> set the priority -> submit to the executor service
        for(Philosopher philosopher : philosophers){
            // create the threads for each philosopher
            Thread thread = new Thread(philosopher);

            // set the priority of the thread based on the philosopher's priority
            thread.setPriority(philosopher.getPriority());

            // set the thread as daemon
            thread.setDaemon(true);

            // submit the philosopher thread to the executor service for execution
            executorService.submit(thread);
        }

        // run the simulation for 35 sec
        try{
            Thread.sleep(35000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            System.out.println("Terminating...");

            // shut down the executor service to terminate all the philosopher threads
            executorService.shutdownNow();
        }
        try{
            if(!executorService.awaitTermination(10, TimeUnit.SECONDS)){
                System.err.println("ExecutorService didn't terminate in time.");
            }else {
                System.out.println("ExecutorService terminated successfully.");
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }




    }

}
