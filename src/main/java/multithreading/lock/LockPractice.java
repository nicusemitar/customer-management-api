package multithreading.lock;

public class LockPractice {
    private int count = 0;  // Shared field

    public void incrementMethod() {
        for (int i = 0; i <= 10; i++) {
            count++;
        }
        System.out.println("Count after incrementing: " + count);
    }

    public int getCount() {
        return count;
    }

}


