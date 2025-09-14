import java.util.Random;
import java.util.concurrent.*;

public class Task2 {
    // Dynamic thread count based on available processors
    private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors();

    /**
     * Generates an array of random integers
     * @param size The size of the array to generate
     * @return Array filled with random integers
     */
    public static int[] generateRandomArray(int size) {
        Random random = new Random(42); // Fixed seed for reproducible results
        int[] data = new int[size];

        System.out.println("Generating " + size + " random integers...");
        for (int i = 0; i < size; i++) {
            data[i] = random.nextInt(100) + 1; // Random numbers 1-100
        }
        System.out.println("Array generation completed.");
        return data;
    }

    /**
     * Calculates sum sequentially for performance comparison
     * @param data The array to sum
     * @return The total sum
     */
    public static long sequentialSum(int[] data) {
        long sum = 0;
        for (int value : data) {
            sum += value;
        }
        return sum;
    }

    /**
     * Calculates sum in parallel using multiple threads
     * Each thread processes an equal-sized portion of the array
     * @param data The array to sum
     * @return The total sum
     * @throws InterruptedException If thread execution is interrupted
     * @throws ExecutionException If thread execution fails
     */
    public static long parallelSum(int[] data) throws InterruptedException, ExecutionException {
        if (data == null) {
            throw new IllegalArgumentException("Data array cannot be null");
        }

        System.out.println("Available processors: " + NUM_THREADS);
        System.out.println("Dividing array of " + data.length + " elements into " + NUM_THREADS + " equal portions");

        // ExecutorService means a pool of threads
        ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);

        // Calculate portion size - ensuring equal division
        int basePortionSize = data.length / NUM_THREADS;
        int remainder = data.length % NUM_THREADS;

        // Array to hold Future results from each thread
        Future<Long>[] futures = new Future[NUM_THREADS];

        int currentIndex = 0;
        for (int i = 0; i < NUM_THREADS; i++) {
            // Calculate start and end for this thread's portion
            final int start = currentIndex;
            int portionSize = basePortionSize + (i < remainder ? 1 : 0); // Distribute remainder
            final int end = start + portionSize;
            currentIndex = end;

            final int threadId = i + 1;

            // Submit a task to the pool
            futures[i] = pool.submit(() -> {
                long partialSum = 0;
                System.out.println("Thread-" + threadId + " processing elements " + start + " to " + (end-1) +
                                 " (portion size: " + (end-start) + ")");

                for (int j = start; j < end; j++) {
                    partialSum += data[j];
                }

                System.out.println("Thread-" + threadId + " completed. Partial sum: " + partialSum);
                return partialSum;
            });
        }

        // Collect results from all threads
        long totalSum = 0;
        for (int i = 0; i < futures.length; i++) {
            long partialSum = futures[i].get();
            totalSum += partialSum;
        }

        pool.shutdown();

        // Wait for all threads to complete
        if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
            pool.shutdownNow();
        }

        return totalSum;
    }

    public static void main(String[] args) {
        // Generate array of random integers as specified in requirements
        int size = 100_000; // As suggested in the hint
        int[] data = generateRandomArray(size);

        System.out.println("\n=== Parallel Sum Calculation Demo ===");
        System.out.println("Array size: " + size + " random integers");
        System.out.println("Computer has " + NUM_THREADS + " processor cores");
        System.out.println();

        try {
            // Sequential calculation for comparison
            System.out.println("--- Sequential Calculation ---");
            long startTime = System.nanoTime();
            long sequentialResult = sequentialSum(data);
            long sequentialTime = System.nanoTime() - startTime;

            System.out.println("Sequential sum: " + sequentialResult);
            System.out.println("Sequential time: " + (sequentialTime / 1_000_000) + " ms");
            System.out.println();

            // Parallel calculation using available processor cores
            System.out.println("--- Parallel Calculation ---");
            startTime = System.nanoTime();
            long parallelResult = parallelSum(data);
            long parallelTime = System.nanoTime() - startTime;

            System.out.println("\nParallel sum: " + parallelResult);
            System.out.println("Parallel time: " + (parallelTime / 1_000_000) + " ms");

            // Verify results match
            boolean resultsMatch = (sequentialResult == parallelResult);
            System.out.println("Results match: " + resultsMatch);

            if (!resultsMatch) {
                System.err.println("ERROR: Sequential and parallel results don't match!");
                System.err.println("Sequential: " + sequentialResult + ", Parallel: " + parallelResult);
            }

            // Performance comparison
            if (parallelTime > 0) {
                double speedup = (double) sequentialTime / parallelTime;
                System.out.println("Speedup: " + String.format("%.2fx", speedup));

                if (speedup > 1.0) {
                    System.out.println("✅ Parallel processing was faster!");
                } else {
                    System.out.println("ℹ️  Sequential was faster (expected for small arrays)");
                }
            }

            // Display portion information
            System.out.println("\n--- Thread Distribution Summary ---");
            int baseSize = size / NUM_THREADS;
            int remainder = size % NUM_THREADS;
            System.out.println("Base portion size: " + baseSize + " elements per thread");
            if (remainder > 0) {
                System.out.println("First " + remainder + " threads get 1 extra element");
            }

        } catch (InterruptedException e) {
            System.err.println("Task was interrupted: " + e.getMessage());
            Thread.currentThread().interrupt(); // Restore interrupt status
        } catch (ExecutionException e) {
            System.err.println("Error during execution: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
