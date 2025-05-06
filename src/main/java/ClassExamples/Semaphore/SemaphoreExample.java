package ClassExamples.Semaphore;

import java.util.concurrent.Semaphore;
public class SemaphoreExample {

        // Semaphore with 2 permits
        private static final Semaphore semaphore = new Semaphore(2);

        // Simulated resource
        static class Task implements Runnable {
            private final int id;

            Task(int id) {
                this.id = id;
            }

            @Override
            public void run() {
                try {
                    System.out.println("Thread " + id + " is waiting for permit...");
                    semaphore.acquire();  // acquire a permit
                    System.out.println("Thread " + id + " acquired permit!");

                    // Simulate critical section
                    Thread.sleep(2000);

                    System.out.println("Thread " + id + " releasing permit.");
                    semaphore.release();  // release the permit
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        public static void main(String[] args) {
            for (int i = 1; i <= 5; i++) {
                new Thread(new Task(i)).start();
            }
        }
    }
