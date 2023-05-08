import java.util.concurrent.atomic.AtomicIntegerArray;
public class RThread implements Runnable {

    private AtomicIntegerArray checker;

    public RThread(int regionMin, int regionMax, AtomicIntegerArray checker, float[] data) {
        this.checker = checker;
        for(int i=regionMin; i<regionMax; i++){
            checker.incrementAndGet((int)(data[i]*16777216));
        }
    }

    public void run () {

        
        
    }
}
