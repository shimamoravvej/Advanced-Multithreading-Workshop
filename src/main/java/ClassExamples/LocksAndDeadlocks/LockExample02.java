package ClassExamples.LocksAndDeadlocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample02 {

        static Lock lock = new ReentrantLock();
        static class MyRunnable implements Runnable {
            private final int data;

            public MyRunnable(int data) {
                this.data = data;
            }

            public void run() {
                LockExample02.operation(data);
            }
        }

        static void operation(int data) {

            lock.lock();
            for(int i = 1; i <= data; i++) {
                for(int j = 1; j <= i; j++) {

                    System.out.print("*");
                }
                System.out.println();
            }
            System.out.println();
            lock.unlock();
        }
        public static void main(String[] args) {
            Thread thread1 = new Thread(new MyRunnable(3));
            Thread thread2 = new Thread(new MyRunnable(5));
            Thread thread3 = new Thread(new MyRunnable(7));

            thread1.start();
            thread2.start();
            thread3.start();
        }
    }