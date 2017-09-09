package main;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
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




}
