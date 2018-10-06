package challenges.time_based_hashtable;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);

        TimeBasedHashTable<String, String> hash = new TimeBasedHashTable<>();

        final ScheduledFuture<?> intervalsUntil2Seconds = scheduler.scheduleAtFixedRate(
                () -> hash.put("hello", "world", TimeUnit.MILLISECONDS, 100), 0L, 50L,
                TimeUnit.MILLISECONDS);
        scheduler.schedule(() -> intervalsUntil2Seconds.cancel(false), 2, TimeUnit.SECONDS);

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ex) {
            // will never occur
        }

        String value = hash.get("hello");
        System.out.println(value);


        final ScheduledFuture<?> intervalsUntil8Seconds = scheduler.scheduleAtFixedRate(
                () -> hash.put("hello", "world", TimeUnit.MILLISECONDS, 100), 0L, 50L,
                TimeUnit.MILLISECONDS);
        scheduler.schedule(() -> intervalsUntil8Seconds.cancel(false), 8, TimeUnit.SECONDS);

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ex) {
            // will never occur
        }

        System.out.println(hash.get("hello"));
    }
}
