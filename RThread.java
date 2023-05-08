public class RThread implements Runnable {

    private int regionMin;
    private int regionMax;
    private int[] checker = new int[1_000_000_000];
    private HashMap<Integer, Float> map = new HashMap<Integer, Float>();
    private float[] data;
    private AtomicInteger index;
    private int uniqueID;

    public RThread(float[] data, AtomicInteger index, int uniqueID) {
		this.regionMin = regionMin;
        this.regionMax = regionMax;
        this.data = data;
        this.index = index;
        this.uniqueID = uniqueID;
    }

    public void run () {

    
    float temp;
    for(int i=0; i<data.length; i++){
        float big = 100_000_000_000;
        temp = data[i]*big;
        if(temp < ((float)(1_000_000_000))*uniqueID || temp > ((float)(1_000_000_000))*(uniqueID+1)){
            continue;
        }
        int temp2 = (int)(temp)/100;
        checker[temp2]++;
        map.put(temp2, data[i]);
    }

    while(index.get() < uniqueID){
        if(index.get() == uniqueID){
            break;
        }
    }
    int index = 0;
    for(int i=0; i<checker.length; i++){
        if(checker[i] > 0){
            for(int j=0; j<checker[i]; j++){
                data[index] = map.get();
                index++;
            }
        }
    }


    }
}
