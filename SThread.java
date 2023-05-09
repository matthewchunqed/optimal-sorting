import java.util.concurrent.atomic.AtomicIntegerArray;

public class SThread implements Runnable {

    private AtomicIntegerArray checker;
    private int regionMin;
    private int regionMax;
    private int uniqueID;
    private float[] data;
    private int start;

    public SThread(float[] data, int uniqueID, AtomicIntegerArray checker, int NUM_THREADS, int start) {
        this.checker = checker;
        this.uniqueID = uniqueID;
        this.regionMin = uniqueID*((16777216/8)/NUM_THREADS);
        this.regionMax = (uniqueID+1)*((16777216/8)/NUM_THREADS);
        this.data = data;
        this.start = start;
    }

    public void run () {
    //region -> [uniqueID/numThreads, (uniqueID+1)/numThreads) 
    for(int i=regionMin; i<regionMax; i++){
        for(int j=0; j<8; j++){
            if(((checker.get(i) >> (3*j)) % (1<<3)) > 0){
            for(int k=0; k<((checker.get(i) >> (3*j)) % (1<<3)); k++){
                data[start] = ((float)(i<<3)+j)/16777216;
                start++;
            }
            }
        }
    }

    }
}
