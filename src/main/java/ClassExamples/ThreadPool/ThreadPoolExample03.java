package ClassExamples.ThreadPool;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolExample03 {
        // Math context with high precision
        private static final MathContext MC = new MathContext(30, RoundingMode.HALF_UP);

        // Compute exp(x) using Taylor Series
        private static BigDecimal exp(BigDecimal x, int terms) {
            BigDecimal sum = BigDecimal.ONE;  // term 0
            BigDecimal term = BigDecimal.ONE;

            for (int n = 1; n < terms; n++) {
                term = term.multiply(x, MC).divide(BigDecimal.valueOf(n), MC);
                sum = sum.add(term, MC);
            }

            return sum;
        }

        public static void main(String[] args) throws InterruptedException, ExecutionException {
            ExecutorService executor = Executors.newFixedThreadPool(4); // 4 threads

            // Input values to compute exp(x)
            List<BigDecimal> inputs = List.of(
                    new BigDecimal("1"),
                    new BigDecimal("2"),
                    new BigDecimal("5"),
                    new BigDecimal("10")
            );

            int terms = 50; // Number of terms in Taylor Series

            // Submit computation tasks
            List<Future<BigDecimal>> results = new ArrayList<>();
            for (BigDecimal x : inputs) {
                Callable<BigDecimal> task = () -> exp(x, terms);
                results.add(executor.submit(task));
            }

            // Print results
            for (int i = 0; i < inputs.size(); i++) {
                BigDecimal x = inputs.get(i);
                BigDecimal result = results.get(i).get(); // wait for completion
                System.out.println("exp(" + x + ") = " + result);
            }

            executor.shutdown();
        }
}
