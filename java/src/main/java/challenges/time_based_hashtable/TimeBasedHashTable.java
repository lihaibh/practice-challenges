package challenges.time_based_hashtable;

import java.util.Hashtable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import com.google.common.base.Optional;

/**
 * Challenge: Implement a hash table interface to put items that has limited time to live. The hash
 * table must support concurrency.
 * 
 * NOTE: Duration to complete 1: 29.47 minutes
 */
public class TimeBasedHashTable<K, V> {
    private Hashtable<K, ScheduledPair<V>> data = new Hashtable<K, ScheduledPair<V>>();
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public synchronized void put(K key, V value, TimeUnit unit, int delay) {
        if (data.get(key) != null) {
            // If the key is already exists we shall cancel the last operation
            // we have to make sure that at this point another thread did not remove the item from
            // the dictionary
            (this.data.get(key)).Future.cancel(false);
        }

        this.data.put(key,
                ScheduledPair.of(value, scheduler.schedule(() -> remove(key), delay, unit)));
    }

    public V get(K key) {
        Optional<ScheduledPair<V>> sOptional = Optional.fromNullable(data.get(key));
        return sOptional.isPresent() ? sOptional.get().Value : null;
    }

    private synchronized void remove(K key) {
        data.remove(key);
    }

    static final class ScheduledPair<V> {
        public V Value;
        public ScheduledFuture<?> Future;

        public ScheduledPair(V value, ScheduledFuture<?> future) {
            this.Value = value;
            this.Future = future;
        }

        public static <R> ScheduledPair<R> of(R value, ScheduledFuture<?> future) {
            return new ScheduledPair<R>(value, future);
        }
    }
}
