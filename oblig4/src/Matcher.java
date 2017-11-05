import java.util.ArrayList;

public class Matcher {

    static final int CHAR_MAX = 255;
    static final char WILDCARD = '_';

    public int find(String haystack, String needle){
//        System.out.println("find haystack " + haystack);
        return boyer_moore_horspool(needle.toCharArray(), haystack.toCharArray());
    }

    public ArrayList<Integer> findAll(String haystack, String needle){

        ArrayList<Integer> result = new ArrayList<>();

        int indexFound = find(haystack, needle);
        int resultIndex = indexFound;
        while (indexFound!=-1){
            result.add(resultIndex);
            haystack = haystack.substring(indexFound + needle.length());
            indexFound = find(haystack, needle);
            resultIndex += indexFound + needle.length();
        }
        return result;
    }


    //based on code from lecture
    private int boyer_moore_horspool(char[] needle, char[] haystack){
        if ( needle.length > haystack.length ){ return -1; }
        int[] bad_shift = new int[CHAR_MAX]; // 256
//        for(int i = 0; i < CHAR_MAX; i++){
//            bad_shift[i] = needle.length;
//        }
        int offset = 0, scan = 0;
        int last = needle.length - 1;
        int maxoffset = haystack.length - needle.length;
        for(int i = 0; i < last; i++){
            bad_shift[needle[i]] = last - i;
        }

        int maxShift = (bad_shift[WILDCARD] == 0 ? needle.length : bad_shift[WILDCARD]);
//        System.out.println("maxShift " + maxShift);
        for(int i = 0; i < CHAR_MAX; i++){
            if (bad_shift[i]== 0 || bad_shift[i]>maxShift){
                bad_shift[i] = maxShift;
            }
        }

//        for (char c : needle) {
//            System.out.println("" + c + ": " + bad_shift[c]);
//        }


        while(offset <= maxoffset){
            for(scan = last; isMatch(needle, haystack, scan, offset); scan--){
                if(scan == 0){ // match found!
                    return offset;
                }
            }
            offset += bad_shift[haystack[offset + last]];
        }
        return -1;
    }

    private boolean isMatch(char[] needle, char[] haystack, int scan, int offset){
//        System.out.println("[scan] " + scan + ", offset " + offset + ", haystack[scan+offset] " + haystack[scan+offset]);
//        System.out.println("needle[scan] " + needle[scan] + " = wc " + (needle[scan] == WILDCARD));
        return needle[scan] == haystack[scan+offset] || needle[scan] == WILDCARD;

    }


    public void prettyPrintMatches(ArrayList<Integer> indexes, String haystack, String needle){

        ArrayList<Match> result = new ArrayList<>();
        for (Integer index : indexes) {
            String match = haystack.substring(index, index + needle.length());
            result.add(new Match(index, match));
        }

        System.out.println("Matches found:" + result);
    }

}
