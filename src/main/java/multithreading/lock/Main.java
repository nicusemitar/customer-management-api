package multithreading.lock;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        LockPractice lockPractice = new LockPractice();

        // Creating multiple threads to see lock contention
        Thread t1 = new LockWorker(lockPractice);
        Thread t2 = new LockWorker(lockPractice);
        Thread t3 = new LockWorker(lockPractice);

        t1.start();
        t2.start();
        t3.start();
    }
}

