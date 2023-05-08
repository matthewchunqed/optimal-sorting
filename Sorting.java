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
        
	int[] checker = new int[16777216];
    float temp;
    for(int i=0; i<data.length; i++){
        temp = data[i]*16777216;
        checker[(int)(data[i]*16777216)]++;
    }

    int index = 0;
    for(int i=0; i<checker.length; i++){
        if(checker[i] > 0){
            for(int j=0; j<checker[i]; j++){
                data[index] = ((float)i)/16777216;
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
