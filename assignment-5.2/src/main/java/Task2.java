import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadLocalRandom;

/**
 * ThreadSafeList: a simple thread-safe wrapper around ArrayList providing
 * add, remove, and size operations. Uses synchronized blocks on the backing list to ensure mutual exclusion.
 */
class ThreadSafeList<T> {
    private final List<T> list = new ArrayList<>();

    // Adds an element to the list. Returns true on success.
    public boolean add(T element) {
        synchronized (list) {
            return list.add(element);
        }
    }

    // Removes a single occurrence of the element from the list. Returns true if removed.
    public boolean remove(T element) {
        synchronized (list) {
            return list.remove(element);
        }
    }

    // Returns current size snapshot
    public int size() {
        synchronized (list) {
            return list.size();
        }
    }

}

/**
 * Task2: test program for ThreadSafeList
 * - Spawns multiple adder threads which add unique items to the ThreadSafeList and
 *   then queue the item id onto a BlockingQueue.
 * - Spawns multiple remover threads that take items from the BlockingQueue and
 *   remove the corresponding element from the ThreadSafeList.
 * - At the end we check the consistency: final size should be zero and removedCount == addedCount.
 */
public class Task2 {
    public static void main(String[] args) throws Exception {
        final ThreadSafeList<String> safeList = new ThreadSafeList<>();
        final BlockingQueue<String> addedQueue = new LinkedBlockingQueue<>();

       final int numAdders = 5, itemsPerAdder = 200, numRemovers = 5, totalAdds = numAdders * itemsPerAdder;

        ExecutorService executor = Executors.newFixedThreadPool(numAdders + numRemovers);

        final AtomicInteger addedCount = new AtomicInteger(0);
        final AtomicInteger removedCount = new AtomicInteger(0);

        // Adder tasks: add to list, then put the id into the queue so removers can remove it
        for (int a = 0; a < numAdders; a++) {
            final int aid = a;
            executor.submit(() -> {
                for (int i = 0; i < itemsPerAdder; i++) {
                    String id = "item-" + aid + "-" + i;
                    boolean added = safeList.add(id);
                    if (added) {
                        addedCount.incrementAndGet();
                        // offer to queue (should not block)
                        addedQueue.offer(id);
                    }
                    // small random sleep to increase interleaving
                    try {
                        Thread.sleep((long) (ThreadLocalRandom.current().nextDouble() * 2));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });
        }

        // Remover tasks: take known ids from queue and remove from list
        for (int r = 0; r < numRemovers; r++) {
            executor.submit(() -> {
                while (removedCount.get() < totalAdds) {
                    String id;
                    try {
                        // wait up to 500 ms for an element; if none and producers finished, break
                        id = addedQueue.poll(500, TimeUnit.MILLISECONDS);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                    if (id == null) {
                        // check termination
                        if (addedCount.get() >= totalAdds) break;
                        else continue;
                    }
                    boolean removed = safeList.remove(id);
                    if (removed) {
                        removedCount.incrementAndGet();
                    }
                }
            });
        }

        executor.shutdown();
        boolean finished = executor.awaitTermination(30, TimeUnit.SECONDS);
        if (!finished) {
            System.err.println("Executor didn't finish in time, forcing shutdown");
            executor.shutdownNow();
        }

        // Validation
        int finalSize = safeList.size();
        int adds = addedCount.get();
        int removes = removedCount.get();

        System.out.println("Adds recorded: " + adds);
        System.out.println("Removes recorded: " + removes);
        System.out.println("Final list size (snapshot): " + finalSize);

        if (finalSize == 0 && adds == removes && adds == totalAdds) {
            System.out.println("SUCCESS: ThreadSafeList passed the concurrent add/remove test");
        } else {
            System.out.println("Test finished with inconsistencies; inspect output.");
        }
    }
}
