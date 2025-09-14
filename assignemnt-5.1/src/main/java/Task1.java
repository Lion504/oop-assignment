import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Task1 {
    // Multi-threaded Number Printing with Enhanced Thread Safety
    private final Lock lock = new ReentrantLock();
    // volatile ensures visibility of changes to variables across threads
    private volatile int currentNum = 1;
    // #isPrintingComplete
    private volatile boolean isPrintingComplete = false;
    // Condition variables to signal threads when it's their turn
    private final Condition oddTurn = lock.newCondition();
    private final Condition evenTurn = lock.newCondition();

    class OddThread extends Thread {
        // max is the upper limit for printing numbers
        private final int max;

        OddThread(int max) {
            this.max = max;
            this.setName("OddThread"); // Better thread identification
        }

        // Main logic for odd number printing
        @Override
        public void run() {
            while (!isPrintingComplete) {
                lock.lock();
                try {
                    // Wait while it's not our turn and printing isn't complete
                    while ((currentNum % 2 == 0 || currentNum > max) && !isPrintingComplete) {
                        if (currentNum > max) {
                            isPrintingComplete = true;
                            evenTurn.signalAll(); // Wake up even thread to exit
                            break;
                        }
                        oddTurn.await();
                    }

                    if (!isPrintingComplete && currentNum <= max && currentNum % 2 == 1) {
                        System.out.println("⚪ " + currentNum + " from " + Thread.currentThread().getName());
                        currentNum++;
                        evenTurn.signal(); // Signal even thread to proceed
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restore interrupt status
                    break; // Exit gracefully on interruption
                } finally {
                    lock.unlock(); // Always unlock in the finally block
                }
            }
            System.out.println(Thread.currentThread().getName() + " finished execution");
        }
    }

    class EvenThread extends Thread {
        private final int max;

        EvenThread(int max) {
            this.max = max;
            this.setName("EvenThread"); // Better thread identification
        }

        @Override
        public void run() {
            while (!isPrintingComplete) {
                lock.lock();
                try {
                    // Wait while it's not our turn and printing isn't complete
                    while ((currentNum % 2 != 0 || currentNum > max) && !isPrintingComplete) {
                        if (currentNum > max) {
                            isPrintingComplete = true;
                            oddTurn.signalAll(); // Wake up odd thread to exit
                            break;
                        }
                        evenTurn.await();
                    }

                    if (!isPrintingComplete && currentNum <= max && currentNum % 2 == 0) {
                        System.out.println("🔵 " + currentNum + " from " + Thread.currentThread().getName());
                        currentNum++;
                        oddTurn.signal(); // Signal odd thread to proceed
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restore interrupt status
                    break; // Exit gracefully on interruption
                } finally {
                    lock.unlock();
                }
            }
            System.out.println(Thread.currentThread().getName() + " finished execution");
        }
    }

    public void startPrinting(int max) throws InterruptedException {
        System.out.println("Starting multi-threaded number printing from 1 to " + max);

        // Create instances of both threads
        Thread oddThread = new OddThread(max);
        Thread evenThread = new EvenThread(max);

        // Start both threads
        oddThread.start();
        evenThread.start();

        // Wait for both threads to complete (proper thread management)
        oddThread.join();
        evenThread.join();

        System.out.println("All threads completed successfully!");
    }

    // Reset method for reusing the same instance
    public void reset() {
        currentNum = 1;
        isPrintingComplete = false;
    }

    public static void main(String[] args) {
        try {
            Task1 printer = new Task1();
            // Start printing numbers up to 20 for better readability in output
            printer.startPrinting(20);

            // Demonstrate reset functionality
            System.out.println("\n--- Resetting and printing again ---\n");
            printer.reset();
            printer.startPrinting(50);

        } catch (InterruptedException e) {
            System.err.println("Main thread was interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
