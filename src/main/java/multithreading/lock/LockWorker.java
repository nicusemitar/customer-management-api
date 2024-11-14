package multithreading.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockWorker extends Thread {

    private final LockPractice lockPractice;

    Lock lock = new ReentrantLock();

    public LockWorker(LockPractice lockPractice) {
        this.lockPractice = lockPractice;
    }

    @Override
    public void run() {
        if (lock.tryLock()) { // Attempt to acquire the lock
            try {
                lockPractice.incrementMethod();
            } finally {
                lock.unlock(); // Ensure the lock is released after use
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " could not acquire the lock.");
        }
    }
}
