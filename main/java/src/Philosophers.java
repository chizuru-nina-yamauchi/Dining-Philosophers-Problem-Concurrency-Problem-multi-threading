
 // represents a philosopher that thinks, eats, and pick up and put down a fork
public class Philosophers implements Runnable{
    // attributes
    // making it final = not meant to be changed or overridden
    private final int philosopherID;
    private final Fork leftFork;
    private final Fork rightFork;
    private final int priority;

    // constructor
    public Philosophers(int philosopherID, Fork leftFork, Fork rightFork, int priority){
        this.philosopherID = philosopherID;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.priority = priority;
    }

    @Override
    public void run(){

        while(true) {
            try{
                // while true, philosopher think, use fork, eat finish using fork
            think();
            pickUpForks();
            eat();
            putDownForks();
            }catch(InterruptedException e){ // handle interruption (ex. thread termination)
                Thread.currentThread().interrupt();
            }
        }
    }

    private void think() throws InterruptedException{
        System.out.println("Philosopher " + philosopherID + " is thinking.");
        Thread.sleep(1000); // simulate thinking time: 1 second duration
    }

    private void pickUpForks() throws InterruptedException{
        System.out.println("Philosopher " + philosopherID + " is waiting for forks.");

        // pick up left fork
        leftFork.pickUp();
        System.out.println("Philosopher " + philosopherID + " picked up left fork.");

        // pick up right fork
        rightFork.pickUp();
        System.out.println("Philosopher " + philosopherID + " picked up right fork.");


    }

    private void eat()throws InterruptedException{
        System.out.println("Philosopher " + philosopherID + " is eating.");
        Thread.sleep(2000); // simulate eating time: 2 sec

    }

    private void putDownForks() throws InterruptedException{
        // put down left fork
        leftFork.putDown();
        System.out.println("Philosopher " + philosopherID + "put down left fork.");

        // put down right fork
        rightFork.putDown();
        System.out.println("Philosopher " + philosopherID + "put down right fork.");
    }

    // getter for the priority of the philosopher
    public int getPriority(){
        return priority;
    }



}
