package main;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sortering {

    final static int NUM_BIT = 6; //6-13 er best
    final static int MIN_NUM = 9; // mellom 16 og 60, kvikksort bruker 47

    final static boolean DEBUG = false;
    static String indent = "\t";


    int findMax(int[] a) {
        int max = 0;
        for (int element : a) {
            if (element > max)
                max = element;
        }
        return max;
    }

    double VRadixMulti(int [] a) {
        long tt = System.nanoTime();
        int [] b = new int[a.length];

        int max = findMax(a);
        int numbBits = findNumberBits(max);
        int maskLength = Math.min(numbBits, NUM_BIT);

        // c) Første kall (rot-kallet) på VenstreRadix med a[], b[] , numBit, og lengden av første siffer
        VenstreRadix(a, b, 0, a.length, numbBits, maskLength);

        double tid = (System.nanoTime() -tt)/1000000.0;
        testSort(a);
        return tid; // returnerer tiden i ms. det tok å sortere a, som nå er sortert og testet
    }

    // Sorter a[left..right] på siffer med start i bit: leftSortBit, og med lengde: maskLen bit,
    void VenstreRadix ( int [] a, int [] b, int left, int right, int leftSortBit, int maskLength){

        if ((right - left) == 0) {
            return;
        }

        if ((right - left) == 1) {
            b[left] = a[left];
            return;
        }

        indent+="\t";

        int mask = (1<<maskLength) - 1;
        int shift = leftSortBit - maskLength;

        int [] count = new int [mask+1]; //array with maskLen**2 slots for all possible numbers in this digit
        //……………. Andre deklarasjoner ……………

        debugPrint("\n");
        debugPrint("******VenstreRadix called : left:" + left + ", right:" + right);
        debugPrint("leftSortBit + " + leftSortBit + ", maskLength:" + maskLength);

        debugPrintArrayInBinary("a", a, left, right);


        //test ->
        debugPrint("mask: " + Integer.toBinaryString(mask) + ", shift: " + shift);
        List<Integer> testdigits = new ArrayList<>();
        //test <-

        // d) count[] =hvor mange det er med de ulike verdiene
        // av dette sifret I a [left..right]
        for (int i = left; i < right; i++) {
            //test ->
            testdigits.add((a[i] >> shift) & mask);
            //test <-
            count[(a[i] >> shift) & mask]++;
        }

        //test ->
        debugPrintArrayInBinary("testdigits", testdigits);
        debugPrint("count: " + Arrays.toString(count));
        //test <-


        // e) Tell opp verdiene I count[] slik at count[i] sier hvor i b[] vi skal
        // flytte første element med verdien ‘i’ vi sorterer.
        int sum = 0;
        for (int i=0; i< count.length ; i++){
            int countValue = count[i];
            count[i] = sum;
            sum+= countValue;
        }
        debugPrint("summert count: " + Arrays.toString(count));


        // f) Flytt tallene fra a[] til b[] sorter på dette sifferet I a[left..right] for
        //alle de ulike verdiene for dette sifferet

        debugPrintArrayInBinary("a", a,  left, right);

        for (int i = left; i < right; i++) {
            int digit = (a[i] >> shift) & mask;
            debugPrint("a[" + i + "]:" + Integer.toBinaryString(a[i]) + ", digit "  + digit + ", left + count[digit] "  + (left + count[digit]));
            b[left + count[digit]] = a[i];
            count[digit]++;
        }
        //test ->
        debugPrint("etter f count: " + Arrays.toString(count));
        debugPrintArrayInBinary("b", b, left, right);
        //test <-


        // g) Kall enten innstikkSort eller rekursivt VenstreRadix
        // på neste siffer (hvis vi ikke er ferdige) for alle verdiene vi har av nåværende siffer
        int bitsLeft = leftSortBit - maskLength;
        if (bitsLeft <= 0){
            // Vurder når vi. skal kopiere tilbake b[] til a[] ??
            for (int i = left; i < right; i++) {
                a[i] = b[i];
            }
            indent = indent.substring(0, indent.length()-1);
            return;
        }
        int start = left;
        for (int i : count) {
            int end = left + i;
            if(end != start) {
                VenstreRadix(b, a, start, left + i, bitsLeft, Math.min(bitsLeft, NUM_BIT));
            }
            start = end;
        }
        debugPrintArrayInBinary("a end", a, left, right);
        indent = indent.substring(0, indent.length()-1);




    }// end VenstreRadix


    boolean testSort(int [] a){
        for (int i = 0; i< a.length-1;i++) {
            if (a[i] > a[i+1]){
                System.out.println("SorteringsFEIL på: "+
                        i +" a["+i+"]:"+a[i]+" > a["+(i+1)+"]:"+a[i+1]);
                return false;
            } }
        return true;
    }


    int findNumberBits(int number){
        int numBits = 1;
        while (number >= (1<<numBits)) numBits++;
        return numBits;
    }

    public static void debugPrint(String message){
        if (DEBUG){
            System.out.println(indent + message);
        }
    }

    public static void debugPrintArrayInBinary(String label, int[] array, int left, int right){

        if (!DEBUG){
            return;
        }


        List<String> res = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            res.add(( i>= left && i<right ? "*" : "" ) + Integer.toBinaryString(array[i]));
        }

        System.out.println(indent + label + ": " + res);
    }

    public static void debugPrintArrayInBinary(String label, List<Integer> array){

        if (!DEBUG){
            return;
        }

        List<String> res = new ArrayList<>();
        for (Integer e: array) {
            res.add(Integer.toBinaryString(e));
        }

        System.out.println(indent + label + ": " + res);
    }

}


