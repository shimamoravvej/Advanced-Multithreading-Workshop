package Workshop.HandlingRaceConditions;

public class SynchronizedWorkshop {
    public static int counter = 0;
    public static class MyRunnable implements Runnable
    {
        @Override
        public void run() {
            int i;
            for (i = 0; i < 1_000_000; i++) {
                synchronized (SynchronizedWorkshop.class) {
                    counter += 1;
                }
            }
            System.out.println("number of increments in " + Thread.currentThread().getName() + ": " + i);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable=  new MyRunnable();
        Thread thread1 = new Thread(new MyRunnable());
        Thread thread2 = new Thread(new MyRunnable());
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println("the value of counter at the end: " + counter);
    }
}