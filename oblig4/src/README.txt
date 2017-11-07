1. An explanation of your algorithm and why it works.
- if the needle has wildcards, we need to reduce bad character shift for all characters
that have bigger bad character shift than the wildcard itself.
1) We find bad character shift for wildcard in needle in the same way as for other elements in the needle:

        int last = needle.length - 1;
        for(int i = 0; i < last; i++){
            bad_shift[needle[i]] = last - i;
        }

2) Then we apply it to all other elements:

         int maxShift = (bad_shift[WILDCARD] == 0 ? needle.length : bad_shift[WILDCARD]);
         for(int i = 0; i < CHAR_MAX; i++){
             if (bad_shift[i]== 0 || bad_shift[i]>maxShift){
                 bad_shift[i] = maxShift;
             }
         }

3) When we check if a character in needle matches a character in the haystack,
we can simply say that wildcard matches anything:

    private boolean isMatch(char[] needle, char[] haystack, int scan, int offset){
        return needle[scan] == haystack[scan+offset] || needle[scan] == WILDCARD;
    }

2. How to compile your program (ie. javac *.java)
javac *.java

3. Which file includes the main-method
Main.java

4. Any assumptions you have made when implementing the assignment
The combinations like __ shouldn't match ALL letter combinations of 2 characters.
 So the result of matching __ with 123456 will be
 {12, 34, 56}, but not {12, 23, 34, 45, 56}

5. Any peculiarities about your implementation
-
6. The status of your delivery (what works and what does not)
Everything works

7. Give credit if your code is heavily influenced by some source (ie. teaching
material)
The credits are in comments in the code
(boyer_moore_horspool() is based on code from lecture,
readFileIntoString() is based on
https://stackoverflow.com/questions/3402735/what-is-simplest-way-to-read-a-file-into-string)

8. Testing is done in MatcherTest class which is being run in Main:

        //test ->
        MatcherTest matcherTest = new MatcherTest();
        matcherTest.test();
        //test <-