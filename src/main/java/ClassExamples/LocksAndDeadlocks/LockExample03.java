package ClassExamples.LocksAndDeadlocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample03 {
    //Reference: https://www.youtube.com/watch?v=MWlqrLiscjQ&ab_channel=JakobJenkov
    public static void main(String[] args) throws InterruptedException {
        LockExample03 lockExample03 = new LockExample03();

        // Create runnable tasks that use the calculator
        Runnable task1 = () -> {
            lockExample03.calculate(
                    new LockExample03.Calculation(LockExample03.Calculation.ADDITION, 100.0),
                    new LockExample03.Calculation(LockExample03.Calculation.SUBTRACTION, 50.0)
            );
        };

        Runnable task2 = () -> {
            lockExample03.calculate(
                    new LockExample03.Calculation(LockExample03.Calculation.ADDITION, 200.0),
                    new LockExample03.Calculation(Calculation.SUBTRACTION, 75.0)
            );
        };

        // Create and start threads
        Thread thread1 = new Thread(task1, "Thread-1");
        Thread thread2 = new Thread(task2, "Thread-2");

        thread1.start();
        thread2.start();

        // Wait for both threads to finish
        thread1.join();
        thread2.join();

        // Access and print the result using synchronized block or exposing result safely
        System.out.println("Final result: " + getResult(lockExample03));
    }
    private static double getResult(LockExample03 lockExample03) {
        lockExample03.lock.lock();
        try {
            return lockExample03.result;
        } finally {
            lockExample03.lock.unlock();
        }
    }

    public static class Calculation {
        public static final int UNSPECIFIED = -1;
        public static final int ADDITION    = 0;
        public static final int SUBTRACTION = 1;
        int type = UNSPECIFIED;

        public double value;

        public Calculation(int type, double value){
            this.type  = type;
            this.value = value;
        }
    }

    private double result = 0.0D;
    Lock lock = new ReentrantLock();

    public void add(double value) {
        try {
            lock.lock();
            this.result += value;
        } finally {
            lock.unlock();
        }
    }

    public void subtract(double value) {
        try {
            lock.lock();
            this.result -= value;
        } finally {
            lock.unlock();
        }
    }

    public void calculate(Calculation ... calculations) {
        try {
            lock.lock();

            for(Calculation calculation : calculations) {
                switch(calculation.type) {
                    case Calculation.ADDITION   : add     (calculation.value); break;
                    case Calculation.SUBTRACTION: subtract(calculation.value); break;
                }
            }
        } finally {
            lock.unlock();
        }
    }

}
