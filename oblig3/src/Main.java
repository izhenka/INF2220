import java.util.Arrays;
import java.util.Random;

public class Main {



    public static void main(String[] args) {

        test(1000);
        timeMeasuring();

    }

    public static void timeMeasuring(){

        int numIterations = 5;
        double[] times = new double[numIterations];
        Sortering s = new Sortering();

        //(int)1E+7,
        int[] nList = {(int)1E+7, (int)1E+6, (int)1E+5, (int)1E+4, 1000, 100};

        System.out.println("\n*** VenstreRadix time test: ***\n");
        for (int n : nList) {
            for (int i = 0; i < numIterations; i++) {
                int[] a = makeRandomArray(n);
                times[i] = s.VRadixMulti(a);
            }
            insertSort(times);
            System.out.println("N=" + n + ", median: " + times[(numIterations/2)]);
        }


        System.out.println("\n*** KvikkSort time test: ***\n");
        for (int n : nList) {
            for (int i = 0; i < numIterations; i++) {
                int[] a = makeRandomArray(n);
                long tt = System.nanoTime();
                Arrays.sort(a);
                double tid = (System.nanoTime() -tt)/1000000.0;
                times[i] = tid;
            }
            insertSort(times);
            System.out.println("N=" + n + ", median: " + times[(numIterations/2)]);
        }



    }



    public static void test(int n){

        System.out.println("Test on " + n);


        int[] a = makeRandomArray(n);
        System.out.println(Arrays.toString(a));


        Sortering s = new Sortering();
        Sortering.debugPrint("Max: " + s.findMax(a));
        Sortering.debugPrint("Bits: " + s.findNumberBits(s.findMax(a)));


        s.VRadixMulti(a);
        if(s.testSort(a)){
            System.out.println("Sorted!");
        }
        System.out.println(Arrays.toString(a));
    }



    public static int[] makeRandomArray(int arraySize){

        int[] result = new int[arraySize];

        Random random = new Random();

        for (int i = 0; i< arraySize; i++){
            result[i] = random.nextInt(arraySize);
        }
        return result;
    }



    static void insertSort(double[] array){
        for (int i = 0; i < array.length-1; i++) {
            if (array[i] > array[i+1]){
                double elemToMove = array[i+1];
                int k = i;
                do{
                    array[k+1] = array[k];
                    k--;
                }while(k>=0 && array[k]>elemToMove);

                array[k+1] = elemToMove;
            }
        }
    }


}
