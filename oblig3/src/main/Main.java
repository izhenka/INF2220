package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {



    public static void main(String[] args) {

        int n = 20;
        int[] a = makeRandomArray(n);
        printArrayInBinary("a", a);


        Sortering s = new Sortering();
        System.out.println("Max: " + s.findMax(a));
        System.out.println("Bits: " + s.findNumberBits(s.findMax(a)));

        s.VRadixMulti(a);
    }

    public static int[] makeRandomArray(int arraySize){

        int[] result = new int[arraySize];

        Random random = new Random();

        for (int i = 0; i< arraySize; i++){
            result[i] = random.nextInt(arraySize);
        }
        return result;
    }

    public static void printArrayInBinary(String label, List<Integer> array){

        List<String> res = new ArrayList<>();
        for (Integer e: array) {
            res.add(Integer.toBinaryString(e));
        }

        System.out.println(label + ": " + res);
    }

    public static void printArrayInBinary(String label, int[] array){

        List<String> res = new ArrayList<>();
        for (int e: array) {
            res.add(Integer.toBinaryString(e));
        }

        System.out.println(label + ": " + res);
    }

}
