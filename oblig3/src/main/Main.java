package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {



    public static void main(String[] args) {


        timeMeasuring();

    }

    public static void timeMeasuring(){

        int numIterations = 5;
        double[] times = new double[numIterations];
        Sortering s = new Sortering();



        //int[] nList = {(int)1E+7, (int)1E+6, (int)1E+5, (int)1E+4, 1000, 100};

        int[] nList = {(int)1E+5, (int)1E+4, 1000, 100};

        for (int n : nList) {
            for (int i = 0; i < numIterations; i++) {
                int[] a = makeRandomArray(n);
                times[i] = s.VRadixMulti(a);
            }

            System.out.println(Arrays.toString(times));
            s.insertSort(times);
            System.out.println("N=" + n + ", median: " + times[(numIterations/2)]);
        }



    }

    public static void test(){

        int n = 10;
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




}
