import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadLocalRandom;

public class Task1 {
    private static final int TOTAL_SEATS = 16;
    private static final boolean[] seats = new boolean[TOTAL_SEATS]; // false = free, true = reserved
    private static int reservedSeats = 0;

    // Collects messages from threads in occurrence order; printed after threads finish to avoid interleaving
    private static final List<String> events = Collections.synchronizedList(new ArrayList<>());

    // Make decision and message collection atomic: check available, record reason, and allocate while holding the lock
    public static synchronized int[] reserveSeatsForCustomer(int requested, int customerId) {
        int available = TOTAL_SEATS - reservedSeats;
        if (available == 0) {
            events.add("Customer-" + customerId + ": No seats left.");
            return null;
        }
        if (requested > available) {
            // Not enough for this request now, but some seats remain
            events.add("Customer-" + customerId + ": Failed to buy " + requested + " tickets (available=" + available + "), retrying...");
            return null;
        }
        // enough seats -> allocate inline
        int[] allocated = new int[requested];
        int idx = 0;
        for (int i = 0; i < TOTAL_SEATS && idx < requested; i++) {
            if (!seats[i]) {
                seats[i] = true;
                allocated[idx++] = i;
            }
        }
        reservedSeats += requested;
        events.add("Customer-" + customerId + ": reserved " + requested + " seat(s): " + Arrays.toString(allocated));
        return allocated;
    }

    public static synchronized int getAvailableSeats() {
        return TOTAL_SEATS - reservedSeats;
    }

    public static void main(String[] args) {
        int numCustomers = 20; // more customers than seats

        // Use a fixed thread pool sized to available processors (but at most numCustomers)
        int nThreads = Math.min(Runtime.getRuntime().availableProcessors(), numCustomers);
        ExecutorService executor = Executors.newFixedThreadPool(Math.max(1, nThreads));

        List<Callable<Void>> tasks = new ArrayList<>();

        for (int i = 0; i < numCustomers; i++) {
            final int id = i + 1;
            tasks.add(() -> {
                int attempts = 0;
                int maxAttempts = 3;

                while (attempts < maxAttempts && !Thread.currentThread().isInterrupted()) {
                    int request = ThreadLocalRandom.current().nextInt(1, 5); // random request between 1 and 4 seats

                    // try to reserve (atomic allocate+record)
                    int[] allocated = reserveSeatsForCustomer(request, id);
                    if (allocated != null) {
                        break; // success
                    }

                    // if there are no seats left the reserveSeatsForCustomer already recorded it
                    if (getAvailableSeats() == 0) {
                        break; // no seats left, stop attempting
                    }

                    attempts++;
                    // small randomized backoff
                    try {
                        Thread.sleep(50 + ThreadLocalRandom.current().nextInt(100));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return null;
                    }
                }

                if (attempts >= maxAttempts) {
                    events.add("Customer-" + id + ": Giving up after " + attempts + " attempts.");
                }
                return null;
            });
        }

        try {
            // invoke all tasks; submit them to executor
            for (Callable<Void> task : tasks) executor.submit(task);
        } finally {
            executor.shutdown(); // no new tasks
            try {
                if (!executor.awaitTermination(3, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                    if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
                        events.add("Executor did not terminate cleanly");
                    }
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }

        // Print collected events in occurrence order so output is deterministic
        synchronized (events) {
            for (String e : events) System.out.println(e);
        }

        // Print final seat map
        System.out.println("\nFinal seat assignments (index 0.." + (TOTAL_SEATS - 1) + "): ");
        for (int i = 0; i < TOTAL_SEATS; i++) {
            System.out.println("Seat " + i + ": " + (seats[i] ? "RESERVED" : "FREE"));
        }
        System.out.println("Total reserved seats: " + reservedSeats + " / " + TOTAL_SEATS);
    }
}