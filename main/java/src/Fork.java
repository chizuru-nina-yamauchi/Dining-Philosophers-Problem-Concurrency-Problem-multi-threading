import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


// represents a fork that philosopher can pick up and put down
public class Fork { // remove the Runnable interface since it is not a task that runs in a separate thread

    // control the access to the fork = using ReentrantLock for flexibility and explicit control over locking
    // making it final = ensures that the reference to the ReentrantLock instance cannot be changed after initialization
    private final Lock lock = new ReentrantLock();

    // take the fork
    public void pickUp(){
        lock.lock(); // prevent other philosophers from accessing the fork
    }

    // release the fork
    public void putDown(){
        lock.unlock(); // allows other philosophers to access the fork after unlocking
    }

    public boolean tryPickUp() throws InterruptedException{
        return lock.tryLock(100, TimeUnit.MILLISECONDS);
    }

}
