package main;


public class Sortering {

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

        // a) finn ‘max’ verdi i a[]
        // b) bestem numBit = høyeste (mest venstre) bit i ‘max’ som ==1
        // c) Første kall (rot-kallet) på VenstreRadix med a[], b[] , numBit, og lengden av første siffer

        double tid = (System.nanoTime() -tt)/1000000.0;
        testSort(a);
        return tid; // returnerer tiden i ms. det tok å sortere a, som nå er sortert og testet
    } // end VRadixMulti

    // Sorter a[left..right] på siffer med start i bit: leftSortBit, og med lengde: maskLen bit,
    void VenstreRadix ( int [] a, int [] b, int left, int right, int leftSortBit, int maskLen){
//        int mask = (1<<maskLen]) - 1;
//        int [] count = new int [mask+1];
        //……………. Andre deklarasjoner ……………

        // d) count[] =hvor mange det er med de ulike verdiene
        // av dette sifret I a [left..right]
        // e) Tell opp verdiene I count[] slik at count[i] sier hvor i b[] vi skal
        // flytte første element med verdien ‘i’ vi sorterer.
        // f) Flytt tallene fra a[] til b[] sorter på dette sifferet I a[left..right] for
        //alle de ulike verdiene for dette sifferet
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
}
