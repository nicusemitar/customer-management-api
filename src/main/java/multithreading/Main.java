package multithreading;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("We are know in thread " + Thread.currentThread().getName());
            System.out.println("Current thread priority is " + Thread.currentThread().getPriority());

        });
        thread.setName("New Worker Thread");
//        thread.setPriority(Thread.MAX_PRIORITY);


        System.out.println("We are know in thread: " + Thread.currentThread().getName() + " before starting");
        thread.start();
        System.out.println("We are know in thread: " + Thread.currentThread().getName() + " after starting");

    }
}
