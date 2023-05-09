import java.util.concurrent.atomic.AtomicIntegerArray;

public class RThread implements Runnable {

    private AtomicIntegerArray checker;
    private int regionMin;
    private int regionMax;
    private int uniqueID;
    private float[] data;
    private int[] count;
    private AtomicIntegerArray shared;

    public RThread(float[] data, int uniqueID, AtomicIntegerArray checker, int NUM_THREADS, AtomicIntegerArray shared) {
        this.checker = checker;
        this.uniqueID = uniqueID;
        this.regionMin = uniqueID*(data.length/NUM_THREADS);
        this.regionMax = (uniqueID+1)*(data.length/NUM_THREADS);
        this.data = data;
        this.shared = shared;
        this.count = new int[shared.length()-1];
    }

    public void run () {
    //region -> [uniqueID/numThreads, (uniqueID+1)/numThreads)  
    int temp;  
    for(int i=regionMin; i<regionMax; i++){
        checker.addAndGet((int)(data[i]*16777216/8), (1 << (3*(((int)(data[i]*16777216)) % 8))));
        //want to reduce checker to 1/8th size -> so each int reps 8 diff. numbers
        count[(int)(data[i]*count.length)]++;
    }

    for(int i=0; i<count.length; i++){
        shared.addAndGet(i+1, count[i]);
    }

    }
}
