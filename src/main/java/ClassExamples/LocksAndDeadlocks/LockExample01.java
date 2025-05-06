package ClassExamples.LocksAndDeadlocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class LockExample01 {
    public static class MyRunnable implements Runnable
    {
        private final Lock lock;
        public MyRunnable(Lock lock)
        {
            this.lock = lock;
        }
        @Override
        public void run() {
            try {
                lock.lock();
                //critical section
                System.out.println("Hi");
                Thread.sleep(5000);
            } catch (InterruptedException ignored) {

            }
            finally {
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Thread thread = new Thread(new MyRunnable(lock));
        Thread thread2 = new Thread(new MyRunnable(lock));
        thread.start();
        thread2.start();

        thread.join();
        thread2.join();
        System.out.println("Done");
    }
}