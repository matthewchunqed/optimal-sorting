import java.util.concurrent.atomic.AtomicIntegerArray;

public class SThread implements Runnable {

    private AtomicIntegerArray checker;
    private int regionMin;
    private int regionMax;
    private int uniqueID;
    private float[] data;
    private int start;
    /*
    SThread will parse the frequency array checker[] to populate the data[] array with sorted elements.
    It will first split up its work for each thread into a regionMin (inclusive) and a regionMax (exclusive)
    Then, it will populate data[], starting at the variable "start", which is equal to the number of elements before the thread's region
    (this was measured using the count AtomicIntegerArray from RThread).
    */
    public SThread(float[] data, int uniqueID, AtomicIntegerArray checker, int NUM_THREADS, int start) {
        this.checker = checker;
        this.uniqueID = uniqueID;
        this.regionMin = uniqueID*((2097152)/NUM_THREADS);
        this.regionMax = (uniqueID+1)*((2097152)/NUM_THREADS);
        this.data = data;
        this.start = start;
    }

    public void run () {
    /*
    parses the region of the checker[] array, then reverts the bijection
    to populate data[].
    */
    for(int i=regionMin; i<regionMax; i++){
        for(int j=0; j<8; j++){
            if(((checker.get(i) >> (3*j)) & 0b111) > 0){
            for(int k=0; k<((checker.get(i) >> (3*j)) & 0b111); k++){
                data[start] = ((float)(i<<3)+j)/16777216;
                start++;
            }
            }
        }
    }

    }
}
