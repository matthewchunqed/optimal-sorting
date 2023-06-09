import java.util.Arrays;
import java.util.concurrent.atomic.AtomicIntegerArray;

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

    int NUM_THREADS_MAX = 32;
	int NUM_THREADS = 32;
    AtomicIntegerArray count = new AtomicIntegerArray(NUM_THREADS_MAX+1);
    AtomicIntegerArray checker = new AtomicIntegerArray(2097152);
    //instantiate the data structures for the frequency tracker and the counter for each thread's region of the frequency tracker.

    Thread[] threads = new Thread[NUM_THREADS_MAX];
    //create, run threads that populate the frequency tracker checker[].
    for(int i=0; i<NUM_THREADS; i++){
        threads[i] = new Thread(new RThread(data, i, checker, NUM_THREADS, count));
    }
    for(int i=0; i<NUM_THREADS; i++){
        threads[i].start();
    }
    try{
        for(int i=0; i<NUM_THREADS; i++){
            threads[i].join();
        }
    }catch(Exception e){

    }

    int start = 0;
    //create, run threads that parse the frequency tracker and populates data[] with sorted result.
    for(int i=0; i<NUM_THREADS_MAX; i++){
        start += count.get(i);
        threads[i] = new Thread(new SThread(data, i, checker, NUM_THREADS_MAX, start));
    }
    for(int i=0; i<NUM_THREADS_MAX; i++){
        threads[i].start();
    }

    try{
        for(int i=0; i<NUM_THREADS_MAX; i++){
            threads[i].join();
        }
    }catch(Exception e){

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
