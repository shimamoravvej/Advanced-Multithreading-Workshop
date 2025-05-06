package ClassExamples.LocksAndDeadlocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample02 {

        static class MyRunnable implements Runnable {
            private final int data;
            private final Lock lock;

            public MyRunnable(int data, Lock lock) {
                this.data = data;
                this.lock = lock;
            }

            public void run() {
                lock.lock();
                LockExample02.operation(data);
                lock.unlock();
            }
        }

        static void operation(int data) {

            for(int i = 1; i <= data; i++) {
                for(int j = 1; j <= i; j++) {

                    System.out.print("*");
                }
                System.out.println();
            }
            System.out.println();
        }
        public static void main(String[] args) {
            Lock lock = new ReentrantLock();

            Thread thread1 = new Thread(new MyRunnable(3, lock));
            Thread thread2 = new Thread(new MyRunnable(5, lock));
            Thread thread3 = new Thread(new MyRunnable(7, lock));

            thread1.start();
            thread2.start();
            thread3.start();
        }
    }