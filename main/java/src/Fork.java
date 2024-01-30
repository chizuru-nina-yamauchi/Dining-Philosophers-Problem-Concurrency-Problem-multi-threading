import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


// represents a fork that philosopher can pick up and put down
public class Fork { // remove the Runnable interface since it is not a task that runs in a separate thread

    // control the access to the fork = ensuring that only one philosopher can pick up the fork at a time
    private Lock lock = new ReentrantLock(); // Lock is more generic = more flexible for testability and more

    // take the fork
    public void pickUp(){
        lock.lock();
    }

    // release the fork
    public void putDown(){
        lock.unlock();
    }

}
