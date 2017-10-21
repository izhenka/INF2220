package main;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sortering {

    final static int NUM_BIT = 2; //6-13 er best
    final static int MIN_NUM = 9; // mellom 16 og 60, kvikksort bruker 47


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
        VenstreRadix(a, b, 0, a.length, numbBits-1, maskLength);

        double tid = (System.nanoTime() -tt)/1000000.0;
        testSort(a);
        return tid; // returnerer tiden i ms. det tok å sortere a, som nå er sortert og testet
    }

    // Sorter a[left..right] på siffer med start i bit: leftSortBit, og med lengde: maskLen bit,
    void VenstreRadix ( int [] a, int [] b, int left, int right, int leftSortBit, int maskLength){
        int mask = (1<<maskLength) - 1;
        int [] count = new int [mask+1]; //array with maskLen**2 slots for all possible numbers in this digit
        //……………. Andre deklarasjoner ……………

        //test ->
        System.out.println("mask: " + Integer.toBinaryString(mask));
        List<Integer> testdigits = new ArrayList<>();
        //test <-

        // d) count[] =hvor mange det er med de ulike verdiene
        // av dette sifret I a [left..right]
        int shift = leftSortBit - maskLength + 1;
        for (int elem:a) {
            //test ->
            testdigits.add((elem >> shift) & mask);
            //test <-
            count[(elem >> shift) & mask]++;
        }

        //test ->
        Main.printArrayInBinary("testdigits", testdigits);
        System.out.println("count: " + Arrays.toString(count));
        //test <-


        // e) Tell opp verdiene I count[] slik at count[i] sier hvor i b[] vi skal
        // flytte første element med verdien ‘i’ vi sorterer.
        int sum = 0;
        for (int i=0; i< count.length ; i++){
            int countValue = count[i];
            count[i] = sum;
            sum+= countValue;
        }
        System.out.println("summert count: " + Arrays.toString(count));


        // f) Flytt tallene fra a[] til b[] sorter på dette sifferet I a[left..right] for
        //alle de ulike verdiene for dette sifferet
        for (int elem : a) {
            int digit = (elem >> shift) & mask;
            b[count[digit]] = elem;
            count[digit]++;
        }

        Main.printArrayInBinary("b", b);


        // g) Kall enten innstikkSort eller rekursivt VenstreRadix
        // på neste siffer (hvis vi ikke er ferdige) for alle verdiene vi har av nåværende siffer
        // Vurder når vi. skal kopiere tilbake b[] til a[] ??
    }// end VenstreRadix


    void testSort(int [] a){
        for (int i = 0; i< a.length-1;i++) {
            if (a[i] > a[i+1]){
                System.out.println("SorteringsFEIL på: "+
                        i +" a["+i+"]:"+a[i]+" > a["+(i+1)+"]:"+a[i+1]);
                return;
            } }
    }


    int findNumberBits(int number){
        int numBits = 1;
        while (number >= (1<<numBits)) numBits++;
        return numBits;
    }
}

