import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException{


        String haystack;
        String needle;
        try {
            haystack = readFileIntoString(args[0]);
            needle = readFileIntoString(args[1]);
        } catch (FileNotFoundException e){
            System.out.println("Error! " + e.getMessage());
            return;
        }

        System.out.println("haystack:\t" + haystack);
        System.out.println("needle:\t\t" + needle);

        Matcher matcher = new Matcher(haystack, needle);

    }


    //from https://stackoverflow.com/questions/3402735/what-is-simplest-way-to-read-a-file-into-string
    public static String readFileIntoString(String filename) throws FileNotFoundException{
        String content = new Scanner(new File(filename)).useDelimiter("\\Z").next();
        return content;
    }


}