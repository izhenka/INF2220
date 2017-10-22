package main;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sortering {

    final static int NUM_BIT = 9; //6-13 er best
    final static int MIN_NUM = 20; // mellom 16 og 60, kvikksort bruker 47

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

        VenstreRadix(a, b, 0, a.length, numbBits, maskLength);

        double tid = (System.nanoTime() -tt)/1000000.0;

        testSort(a);
        return tid;
    }

    // Sorter a[left..right] på siffer med start i bit: leftSortBit, og med lengde: maskLen bit,
    void VenstreRadix ( int [] a, int [] b, int left, int right, int leftSortBit, int maskLength){

        debugIncreaseIndent();

        int mask = (1<<maskLength) - 1;
        int shift = leftSortBit - maskLength;
        int [] count = new int [mask+1]; //array with maskLen**2 slots for all possible numbers in this digit


        debug1(left, right, maskLength, mask, leftSortBit, a, shift, count);


        //counting number occurrences of each value in a
        for (int i = left; i < right; i++) {
            count[(a[i] >> shift) & mask]++;
        }


        debug2(left, right, maskLength, mask, leftSortBit, a, shift, count);


        //summing up all values in count to find out position in b for each element in a
        int sum = 0;
        for (int i=0; i< count.length ; i++){
            int countValue = count[i];
            count[i] = sum;
            sum+= countValue;
        }

        debug3(left, right, maskLength, mask, leftSortBit, a, shift, count);


        // Moving from a to b, using computed positions in count
        for (int i = left; i < right; i++) {
            int digit = (a[i] >> shift) & mask;
            debug4(left, i, a, digit, count);
            b[left + count[digit]] = a[i];
            count[digit]++;
        }


        debug5(left, right, maskLength, mask, leftSortBit, b, shift, count);


        //nothing more to sort in this bit, copying back to a
        int bitsLeft = leftSortBit - maskLength;
        if (bitsLeft <= 0){
            copyArrayPart(b, a, left, right);
            debugDecreaseIndent();
            return;
        }

        // start sorting on next digit
        int start = left;
        for (int i : count) {
            int end = left + i;
            if(end == start) { //no elements
                continue;
            }else if (end - start == 1) { //1 element doesn't need sorting
                a[start] = b[start];
            }else if (end - start > MIN_NUM){
                insertSort(b, start, end);
                copyArrayPart(b, a, left, right);
            }else{
                VenstreRadix(b, a, start, left + i, bitsLeft, Math.min(bitsLeft, NUM_BIT));
            }
            start = end;
        }

        debugPrintArrayInBinary("a end", a, left, right);
        debugDecreaseIndent();

    }// end VenstreRadix


    void insertSort(int[] array, int left, int right){

        debugPrint("***Insert sort! left: " + left + ", right: " + right);

        for (int i = left; i < right-1; i++) {
            if (array[i] > array[i+1]){
                int elemToMove = array[i+1];
                int k = i;
                do{
                    array[k+1] = array[k];
                    k--;
                }while(k>=left && array[k]>elemToMove);

                array[k+1] = elemToMove;
            }
        }
    }

    void insertSort(double[] array){
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

    void copyArrayPart(int[] source, int[] dest, int left, int right){
        for (int i = left; i < right; i++) {
            dest[i] = source[i];
        }
    }

    boolean testSort(int [] a){
        for (int i = 0; i< a.length-1;i++) {
            if (a[i] > a[i+1]){
                System.out.println("SorteringsFEIL på: "+
                        i +" a["+i+"]:"+a[i]+" > a["+(i+1)+"]:"+a[i+1]);
                return false;
            } }
        return true;
    }

    int findNumberBits(int number) {
        int numBits = 1;
        while (number >= (1 << numBits)) numBits++;
        return numBits;
    }

    public static void debugIncreaseIndent() {
        if (DEBUG) {
            indent += "\t";
        }
    }

    public static void debugDecreaseIndent() {
        if (DEBUG) {
            indent = indent.substring(0, indent.length() - 1);
        }
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

    private void debug1(int left, int right, int maskLength, int mask, int leftSortBit, int[] a, int shift, int[] count){

        //debug ->
        debugPrint("\n");
        debugPrint("******VenstreRadix called : left:" + left + ", right:" + right);
        debugPrint("leftSortBit + " + leftSortBit + ", maskLength:" + maskLength);
        debugPrintArrayInBinary("a", a, left, right);
        debugPrint("mask: " + Integer.toBinaryString(mask) + ", shift: " + shift);
        //debug <-

    }

    private void debug2(int left, int right, int maskLength, int mask, int leftSortBit, int[] a, int shift, int[] count){
        //debug ->
        List<Integer> testdigits = new ArrayList<>();
        for (int i = left; i < right; i++) {
            testdigits.add((a[i] >> shift) & mask);
        }
        debugPrintArrayInBinary("testdigits", testdigits);
        debugPrint("count: " + Arrays.toString(count));
        //debug <-
    }


    private void debug3(int left, int right, int maskLength, int mask, int leftSortBit, int[] a, int shift, int[] count){
        debugPrint("summert count: " + Arrays.toString(count));
        debugPrintArrayInBinary("a", a,  left, right);
    }



    private void debug4(int left, int i, int[] a, int digit, int[] count){
        debugPrint("a[" + i + "]:" + Integer.toBinaryString(a[i]) + ", digit "  + digit + ", left + count[digit] "  + (left + count[digit]));
    }



    private void debug5(int left, int right, int maskLength, int mask, int leftSortBit, int[] b, int shift, int[] count){
        //debug ->
        debugPrint("etter f count: " + Arrays.toString(count));
        debugPrintArrayInBinary("b", b, left, right);
        //debug <-

    }

}


