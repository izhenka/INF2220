import java.util.ArrayList;

public class Matcher {

    static final int CHAR_MAX = 255;

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


    //from lecture
    private int boyer_moore_horspool(char[] needle, char[] haystack){
        if ( needle.length > haystack.length ){ return -1; }
        int[] bad_shift = new int[CHAR_MAX]; // 256
        for(int i = 0; i < CHAR_MAX; i++){
            bad_shift[i] = needle.length;
        }
        int offset = 0, scan = 0;
        int last = needle.length - 1;
        int maxoffset = haystack.length - needle.length;
        for(int i = 0; i < last; i++){
            bad_shift[needle[i]] = last - i;
        }
        while(offset <= maxoffset){
            for(scan = last; needle[scan] == haystack[scan+offset]; scan--){
                if(scan == 0){ // match found!
                    return offset;
                }
            }
            offset += bad_shift[haystack[offset + last]];
        }
        return -1;
    }


}
