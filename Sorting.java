import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Sorting {
    // replace with your 
    public static final String TEAM_NAME = "Benchmark";
    
    /**
     * Sorts an array of doubles in increasing order. This method is a
     * single-threaded baseline implementation.
     *
     * @param data   the array of doubles to be sorted
     */
    public static void baselineSort (float[] data) {
	Arrays.sort(data, 0, data.length);
    }

    /**
     * Sorts an array of doubles in increasing order. This method is a
     * multi-threaded optimized sorting algorithm. For large arrays (e.g., arrays of size at least 1 million) it should be significantly faster than baselineSort.
     *
     * @param data   the array of doubles to be sorted
     * 
     *
     */

    public static void parallelSort (float[] data) {
        
	    int NUM_THREADS = 16;
    AtomicInteger lock = new AtomicInteger(0);
    AtomicInteger index = new AtomicInteger(0);
    Thread[] threads = new Thread[NUM_THREADS];
    for(int i=0; i<NUM_THREADS; i++){
        threads[i] = new Thread(new RThread(data, i, lock, index, NUM_THREADS));
    }
    for(int i=0; i<NUM_THREADS; i++){
        threads[i].start();
    }

    }

    /**
     * Determines if an array of doubles is sorted in increasing order.
     *
     * @param   data  the array to check for sortedness
     * @return        `true` if the array is sorted, and `false` otherwise
     */
    public static boolean isSorted (float[] data) {
	double prev = data[0];

	for (int i = 1; i < data.length; ++i) {
	    if (data[i] < prev) {
		return false;
	    }

	    prev = data[i];
	}

	return true;
    }
}
