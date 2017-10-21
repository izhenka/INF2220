package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {



    public static void main(String[] args) {

        int n = 100;
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
