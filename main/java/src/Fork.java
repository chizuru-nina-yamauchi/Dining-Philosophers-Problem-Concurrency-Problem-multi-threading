import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


// represents a fork that philosopher can pick up and put down
public class Fork { // remove the Runnable interface since it is not a task that runs in a separate thread

    // control the access to the fork with lock = ensuring that only one philosopher can pick up the fork at a time
    // making it final = ensures that the reference to the ReentrantLock instance cannot be changed, preventing potential issues related to concurrency amd thread safety
    private final Lock lock = new ReentrantLock(); // Lock is more generic = more flexible for testability and more

    // take the fork
    public void pickUp(){
        lock.lock();
    }

    // release the fork
    public void putDown(){
        lock.unlock();
    }

}
