
// represents a philosopher that thinks, eats, and pick up and put down a fork
public class Philosopher implements Runnable{
    // attributes
    // making them final = indicates that these values won't be changed after construction, but the objects themselves are not immutable
    private final int philosopherID;
    private final Fork leftFork;
    private final Fork rightFork;
    private final int priority;

    // constructor
    public Philosopher(int philosopherID, Fork leftFork, Fork rightFork, int priority){
        this.philosopherID = philosopherID;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.priority = priority;
    }

    // run method for thread execution
    @Override
    public void run(){
            try{
                while(!Thread.currentThread().isInterrupted()){
                    // while not interrupted, philosopher think, use fork, eat finish using fork
                    System.out.println("Philosopher " + philosopherID + " is starting activities.");
                    think();
                    pickUpForks();
                    eat();
                    putDownForks();
                    System.out.println("Philosopher " + philosopherID + " finished activities.");
                }

            }catch(InterruptedException e){ // handle interruption (ex. thread termination)
                System.out.println("Philosopher " + philosopherID + " was interrupted.");
                Thread.currentThread().interrupt();
            }

    }

    // simulates thinking time
    private void think() throws InterruptedException{
        System.out.println("Philosopher " + philosopherID + " is thinking.");
        Thread.sleep(2000); // simulate thinking time: 2 second duration
    }
    // philosopher picks up both left and right forks

    private void pickUpForks() throws InterruptedException {
        System.out.println("Philosopher " + philosopherID + " is waiting for forks.");

        // pick up left fork
        while (!leftFork.tryPickUp()) {
            System.out.println("Philosopher " + philosopherID + " couldn't pick up left fork.");
            Thread.sleep(1000); // wait for a bit before retrying
            System.out.println("Philosopher " + philosopherID + " is waiting for a second before retrying.");
        }

        System.out.println("Philosopher " + philosopherID + " picked up left fork.");

        // introduce a small delay to avoid potential deadlock
        Thread.sleep(1000);

        // pick up right fork with a wait
        while (!rightFork.tryPickUp()) {
            System.out.println("Philosopher " + philosopherID + " couldn't pick up right fork.");
            leftFork.putDown(); // put down the left fork if right fork cannot be picked up
            System.out.println("Philosopher " + philosopherID + " put down the left fork to retry.");
            Thread.sleep(1000); // wait for a bit before retrying
            System.out.println("Philosopher " + philosopherID + " is waiting for a second before retrying.");
            // pick up left fork again before retrying to pick up the right fork
            while (!leftFork.tryPickUp()) {
                System.out.println("Philosopher " + philosopherID + " couldn't pick up left fork.");
                Thread.sleep(1000); // wait for a bit before retrying
                System.out.println("Philosopher " + philosopherID + " is waiting for a second before retrying.");
            }

            System.out.println("Philosopher " + philosopherID + " picked up left fork again.");
        }
        System.out.println("Philosopher " + philosopherID + " picked up right fork.");
        Thread.sleep(1000);
    }

    // simulates eating time
    private void eat()throws InterruptedException{
        System.out.println("Philosopher " + philosopherID + " is eating.");
        Thread.sleep(2000); // simulate eating time: 2 sec

    }
    // philosopher puts down both left and right forks
    private void putDownForks() throws InterruptedException{
        // put down left fork
        leftFork.putDown();
        System.out.println("Philosopher " + philosopherID + " put down left fork.");

        // put down right fork
        rightFork.putDown();
        System.out.println("Philosopher " + philosopherID + " put down right fork.");
    }

    // getter for the priority of the philosopher
    public int getPriority(){
        return priority;
    }


}
