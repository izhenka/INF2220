package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            loadDictionary("dictionary.txt");
        } catch (Exception e) {
            System.out.println("Dictionary hasn't been loaded :( \n" + e.getMessage());
            return;
        }

        menu();
    }



    public static void menu(){

        Scanner scanner = new Scanner(System.in);
        String input = "";

        while(!input.equals("q")){
            System.out.println("Type a word:");
            input = scanner.nextLine();
            System.out.println("You are a " + input + "!");
        }
    }


    public static void loadDictionary(String filename) throws Exception {

        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        int n = 0;
        while (scanner.hasNextLine()) {
//            System.out.println(scanner.nextLine());
            scanner.nextLine();
            n++;
        }

        System.out.println(n);


    }


}
