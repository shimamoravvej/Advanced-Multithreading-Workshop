package ClassExamples.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample01 {

        public static void main(String[] args) {
            ExecutorService executor = Executors.newFixedThreadPool(3);

            for (int i = 1; i <= 100; i++) {
                int taskId = i;
                executor.execute(() -> {
                    System.out.println("Running task " + taskId + " on thread " + Thread.currentThread().getName());
                });
            }

            executor.shutdown();
        }
}
