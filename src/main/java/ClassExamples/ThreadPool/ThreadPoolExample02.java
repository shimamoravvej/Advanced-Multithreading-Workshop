package ClassExamples.ThreadPool;

import java.util.concurrent.*;

public class ThreadPoolExample02 {
    public static void main(String[] args)
    {
        int corePoolSize = 10;
        int maxPoolSize = 10;
        int keepAliveTime = 2000;

        ExecutorService executorService = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(128)
        );

        }
}
