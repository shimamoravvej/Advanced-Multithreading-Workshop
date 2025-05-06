package ClassExamples.ThreadPool;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class ThreadPoolExample03 {
        private static final MathContext MC = new MathContext(30, RoundingMode.HALF_UP);
        private static BigDecimal sum = BigDecimal.ZERO;

        public static synchronized void add(BigDecimal value) {
            sum = sum.add(value, MC);
        }

        public static synchronized BigDecimal get() {
            return sum;
        }
        public static void main(String[] args) throws InterruptedException {
            ExecutorService executor = Executors.newFixedThreadPool(4);

            BigDecimal x = new BigDecimal("1");
            int terms = 50;

            for (int n = 0; n < terms; n++) {
                final int termIndex = n;
                executor.execute(() -> {
                    BigDecimal term = computeTerm(x, termIndex);
                    add(term);
                });
            }

            executor.shutdown();
            while (!executor.isTerminated()) {
                Thread.sleep(50);
            }

            System.out.println("exp(" + x + ") = " + get());
        }

        // Compute single term: x^n / n!
        private static BigDecimal computeTerm(BigDecimal x, int n) {
            if (n == 0) return BigDecimal.ONE;

            BigDecimal numerator = BigDecimal.ONE;
            for (int i = 0; i < n; i++) {
                numerator = numerator.multiply(x, MC);
            }

            BigDecimal denominator = BigDecimal.ONE;
            for (int i = 2; i <= n; i++) {
                denominator = denominator.multiply(BigDecimal.valueOf(i), MC);
            }

            return numerator.divide(denominator, MC);
        }

    }
