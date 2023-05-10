import java.util.concurrent.atomic.AtomicIntegerArray;

public class RThread implements Runnable {

    private AtomicIntegerArray checker;
    private int regionMin;
    private int regionMax;
    private float[] data;
    private int[] count;
    private AtomicIntegerArray shared;

    /*RThread will parse the INPUT array, and modify the frequency array checker[]. 
    Since multiple threads may access the same elements at once, checker[] must be an atomic array.
    count[] is the array storing how many elements are in each segment of checker[], 
    that will dictate the expected number of hits each thread will see in the parallelization of the data[] populating.
    */
    public RThread(float[] data, int uniqueID, AtomicIntegerArray checker, int NUM_THREADS, AtomicIntegerArray shared) {
        this.checker = checker;
        this.regionMin = uniqueID*(data.length/NUM_THREADS);
        this.regionMax = (uniqueID+1)*(data.length/NUM_THREADS);
        this.data = data;
        this.shared = shared;
        this.count = new int[shared.length()-1];
    }

    public void run () {

    /*applies the bijection to compress the checker[] array size, then populates checker[].
    also records that the count of elements in that "region" of the checker array goes up by one.
    */
    for(int i=regionMin; i<regionMax; i++){
        checker.addAndGet(((int)(data[i]*16777216)) >> 3, (1 << (3*(((int)(data[i]*16777216)) & 0b111))));
        count[(int)(data[i]*count.length)]++;
    }
    /*applies the bijection to compress the checker[] array size, then populates checker[].
    also records that the count of elements in that "region" of the checker array goes up by one.
    */

    //at the end, it atomically increases shared.
    for(int i=0; i<count.length; i++){
        shared.addAndGet(i+1, count[i]);
    }

    }
}
