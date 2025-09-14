import java.util.concurrent.*;

public class Task2 {
    // parallel summation
    private static final int NUM_THREADS = 16;

    public static long parallelSum(int[] data) throws InterruptedException, ExecutionException {
        if (data == null) {
            throw new IllegalArgumentException("Data array cannot be null");
        }

        //ExecutorService means a pool of threads
        ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
        // checkSize doing ceiling division to make sure we cover all elements
        int checkSize =  (data.length + NUM_THREADS - 1) / NUM_THREADS;

        // Array to hold Future results from each thread
        Future<Long>[] futures = new Future[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            final int start = i * checkSize;// final because used in inner class
            final int end = Math.min(start + checkSize, data.length);// to avoid going out of bounds
            // submit a task to the pool
            futures[i] = pool.submit(() -> {
                long sum = 0;
                for (int j = start; j < end; j++) {
                    sum += data[j];
                }
                return sum;
            });
        }

        long totalSum = 0;
        for (Future<Long> future : futures) {
            totalSum += future.get();
        }

        pool.shutdown();
        return totalSum;
    }

    public static void main(String[] args) {
        int size = 1_000_000;
        int[] data = new int[size];
        for (int i = 0; i < data.length; i++) data[i] = 1; // Fill array with values 1 to size

        try {
            long startTime = System.nanoTime();
            long sum = parallelSum(data);
            long endTime = System.nanoTime();

            System.out.println("Sum: " + sum);
            System.out.println("Time taken: " + (endTime - startTime) / 1_000_000 + " ms");

        } catch (InterruptedException e) {
            System.err.println("Task was interrupted: " + e.getMessage());
            Thread.currentThread().interrupt(); // Restore interrupt status
        } catch (ExecutionException e) {
            System.err.println("Error during execution: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
