package ClassExamples.LocksAndDeadlocks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample04 {
        private static final ReentrantLock lock = new ReentrantLock(true); // true = fair lock

        public static void main(String[] args) throws InterruptedException {
            System.out.println("Is fair lock: " + lock.isFair());

            Thread t1 = new Thread(() -> {
                lock.lock();
                try {
                    System.out.println("Thread-1 acquired lock.");
                    System.out.println("Is locked: " + lock.isLocked());
                    System.out.println("Hold count: " + lock.getHoldCount());
                    System.out.println("Held by current thread: " + lock.isHeldByCurrentThread());

                    // Reentrant call (same thread can lock again)
                    lock.lock();
                    try {
                        System.out.println("Thread-1 re-acquired lock.");
                        System.out.println("Hold count after re-acquire: " + lock.getHoldCount());
                    } finally {
                        lock.unlock();
                    }

                    // Sleep while holding the lock
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {
                } finally {
                    lock.unlock();
                    System.out.println("Thread-1 released lock.");
                }
            });

            Thread t2 = new Thread(() -> {
                try {
                    // Wait briefly to ensure t1 acquires the lock first
                    Thread.sleep(100);
                    System.out.println("Thread-2 trying tryLock()...");
                    if (lock.tryLock()) {
                        try {
                            System.out.println("Thread-2 acquired lock using tryLock.");
                        } finally {
                            lock.unlock();
                        }
                    } else {
                        System.out.println("Thread-2 could NOT acquire lock with tryLock().");
                    }

                    System.out.println("Thread-2 trying tryLock(timeout)...");
                    if (lock.tryLock(3, TimeUnit.SECONDS)) {
                        try {
                            System.out.println("Thread-2 acquired lock using tryLock(timeout).");
                        } finally {
                            lock.unlock();
                        }
                    } else {
                        System.out.println("Thread-2 could NOT acquire lock within timeout.");
                    }

                } catch (InterruptedException e) {
                    System.out.println("Thread-2 interrupted.");
                }
            });

            t1.start();
            t2.start();

            t1.join();
            t2.join();

            System.out.println("Is locked at end: " + lock.isLocked());
            System.out.println("Hold count at end: " + lock.getHoldCount());
        }
    }
