import java.util.ArrayList;

public class MatcherTest {

    Matcher matcher;
    boolean failed = false;

    public void test(){

        matcher = new Matcher();
        test_position_0();
        test_position_in_the_middle();
        test_position_at_the_end();
        test_no_position();
        test_1_wildcard_first_char();
        test_1_wildcard_last_char();
        test_just_2_wildcards();
        test_2_wildcards_with_char();
        test_empty_haystack();
        test_empty_needle();

        if (!failed){
            System.out.println("All test passed!");
        }
    }

    public void checkIfAsExpected(String haystack, String needle, ArrayList<Match>expected){

        ArrayList<Match> result = matcher.findMatches(haystack, needle);
        if (!result.equals(expected)){
            System.out.println("haystack:\t" + haystack);
            System.out.println("needle\t\t" + needle);
            System.out.println("Expected " + expected + ", but result is " + result);
            failed = true;
        }

    }

    private void test_position_0(){
        String haystack = "0123456789";
        String needle = "0";

        ArrayList<Match> expected = new ArrayList<>();
        expected.add(new Match(0, "0"));

        checkIfAsExpected(haystack, needle, expected);
    }

    private void test_position_in_the_middle(){
        String haystack = "0123456789";
        String needle = "5";

        ArrayList<Match> expected = new ArrayList<>();
        expected.add(new Match(5, "5"));

        checkIfAsExpected(haystack, needle, expected);
    }

    private void test_position_at_the_end(){
        String haystack = "0123456789";
        String needle = "9";

        ArrayList<Match> expected = new ArrayList<>();
        expected.add(new Match(9, "9"));

        checkIfAsExpected(haystack, needle, expected);
    }

    private void test_no_position(){
        String haystack = "0123456789";
        String needle = "99";

        ArrayList<Match> expected = new ArrayList<>();

        checkIfAsExpected(haystack, needle, expected);
    }

    private void test_1_wildcard_last_char(){
        String haystack = "0123456789 123*56789";
        String needle = "3_";

        ArrayList<Match> expected = new ArrayList<>();
        expected.add(new Match(3, "34"));
        expected.add(new Match(13, "3*"));

        checkIfAsExpected(haystack, needle, expected);
    }

    private void test_1_wildcard_first_char(){
        String haystack = "0123456789 1*3456789";
        String needle = "_3";

        ArrayList<Match> expected = new ArrayList<>();
        expected.add(new Match(2, "23"));
        expected.add(new Match(12, "*3"));

        checkIfAsExpected(haystack, needle, expected);
    }

    private void test_just_2_wildcards(){
        String haystack = "0123456789**-";
        String needle = "__";

        ArrayList<Match> expected = new ArrayList<>();
        expected.add(new Match(0, "01"));
        expected.add(new Match(2, "23"));
        expected.add(new Match(4, "45"));
        expected.add(new Match(6, "67"));
        expected.add(new Match(8, "89"));
        expected.add(new Match(10, "**"));

        checkIfAsExpected(haystack, needle, expected);
    }

    private void test_2_wildcards_with_char(){
        String haystack = "0123456789 123abc789 123a5c789 123*5*789";
        String needle = "_5_";

        ArrayList<Match> expected = new ArrayList<>();
        expected.add(new Match(4, "456"));
        expected.add(new Match(24, "a5c"));
        expected.add(new Match(34, "*5*"));

        checkIfAsExpected(haystack, needle, expected);
    }


    private void test_empty_haystack(){
        String haystack = "";
        String needle = "_5_";

        ArrayList<Match> expected = new ArrayList<>();

        checkIfAsExpected(haystack, needle, expected);
    }

    private void test_empty_needle(){
        String haystack = "0123456789 123abc789 123a5c789 123*5*789";
        String needle = "";

        ArrayList<Match> expected = new ArrayList<>();

        checkIfAsExpected(haystack, needle, expected);
    }
}
