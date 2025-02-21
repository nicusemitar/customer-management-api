package multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptiblyExample {
    private final Lock lock = new ReentrantLock();

    public void performTask() {
        try {
            lock.lockInterruptibly();
            try {
                System.out.println("Lock acquired, performing task");
                Thread.sleep(2000); // Simulate task work
            } finally {
                lock.unlock();
                System.out.println("Lock released");
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted while waiting for the lock");
        }
        // Check if interrupted after task completion
        if (Thread.currentThread().isInterrupted()) {
            System.out.println("Thread was interrupted during execution");
        }
    }

    public static void main(String[] args) {
        LockInterruptiblyExample example = new LockInterruptiblyExample();

        Thread t1 = new Thread(example::performTask);
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(500); // Let t1 acquire the lock first
                t1.interrupt(); // Interrupt t1 while it's working (if it's not waiting)
            } catch (InterruptedException ignored) {}
        });

        t1.start();
        t2.start();
    }
}
