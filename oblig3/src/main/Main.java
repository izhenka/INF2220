package main;

import java.util.Arrays;
import java.util.Random;

public class Main {

    final static int NUM_BIT = 9; //6-13 er best
    final static int MIN_NUM = 9; // mellom 16 og 60, kvikksort bruker 47


    public static void main(String[] args) {

        int n = 50;
        int[] a = makeRandomArray(n);
        System.out.println(Arrays.toString(a));

        Sortering s = new Sortering();
        System.out.println("Max: " + s.findMax(a));

        s.testSort(a);
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
