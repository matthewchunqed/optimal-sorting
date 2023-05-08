import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.HashMap;

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
     */
    public static void parallelSort (float[] data) {

/*    HashMap<Integer, Float> map = new HashMap<Integer, Float>();
    int NUM_THREADS = 100;
    Thread[] threads = new Thread[NUM_THREADS];
	// replace this with your method!
	int[] checker = new int[1_000_000_000];
    AtomicInteger index = new AtomicInteger(0);
    for (int i = 0; i < NUM_THREADS; i++) {
		threads[i] = new Thread(new RThread(data, index, i));
        threads[i].start();
	}
    */

    HashMap<Integer, Float> map = new HashMap<Integer, Float>();
	int[] checker = new int[16777216];
    float temp;
    for(int i=0; i<data.length; i++){
        temp = data[i]*16777216;
        checker[(int)temp]++;
        map.put((int)temp, data[i]);
    }

    int index = 0;
    for(int i=0; i<checker.length; i++){
        if(checker[i] > 0){
            for(int j=0; j<checker[i]; j++){
                data[index] = map.get(i);
                index++;
            }
        }
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
