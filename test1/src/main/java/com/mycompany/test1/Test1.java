public class Test1 {
    private static final Object LOCK = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread waiter = new Thread(() -> {
            synchronized (LOCK) {
                try { LOCK.wait(); }                     // → WAITING
                catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "waiter");
        Thread sleeper = new Thread(() -> {
            try { Thread.sleep(5_000); }                 // → TIMED_WAITING
            catch (InterruptedException e) { /* woken early */ }
        }, "sleeper");
        System.out.println(waiter.getState());           // NEW
        waiter.start(); sleeper.start();
        Thread.sleep(100);  // give them time to block
        System.out.println(waiter.getState());           // WAITING
        System.out.println(sleeper.getState());          // TIMED_WAITING
        synchronized (LOCK) { LOCK.notifyAll(); }        // wake waiter
        sleeper.interrupt();  // wake sleeper early
        waiter.join(); sleeper.join();
        System.out.println(waiter.getState());           // TERMINATED
    }
}