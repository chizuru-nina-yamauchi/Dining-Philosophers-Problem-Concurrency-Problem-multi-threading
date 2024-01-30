

public class Philosophers implements Runnable{
    // attributes
    private int philosopherID;
    private Fork leftFork;
    private Fork rightFork;
    private int priority;

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
            think();
            pickUpForks();
            eat();
            putDownForks();
            }catch(InterruptedException e){

            }
        }
    }

    private void think() throws InterruptedException{


    }

    private void pickUpForks() throws InterruptedException{

    }

    private void eat()throws InterruptedException{

    }

    private void putDownForks() throws InterruptedException{

    }



}
