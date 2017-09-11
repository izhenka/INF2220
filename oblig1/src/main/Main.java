package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static BinaryTree binaryTree = new BinaryTree();

    public static void main(String[] args) {
        try {
            loadDictionary("dictionary.txt");
        } catch (Exception e) {
            System.out.println("Dictionary hasn't been loaded :( \n" + e.getMessage());
            return;
        }

        menu();
        printOutStatistics();
    }


    public static void printOutStatistics(){
        System.out.println("\n****** Statistics ******");
        System.out.println("Tree depth: " + binaryTree.getHeight());
        System.out.println("---------------------------");

        System.out.println("Nodes per depth:");
        int[] nodesPerDepth = binaryTree.getNodesPerDepth();
        int n=0;
        int nodesCount = 0;
        int sumDepth = 0;
        for (int nodes: nodesPerDepth
             ) {
            System.out.println("Depth " + n + ": " + nodes);
            n++;
            nodesCount += nodes;
            sumDepth += n*nodes;
        }
        System.out.println("---------------------------");

        System.out.println("Average depth: " + (sumDepth/nodesCount));
        System.out.println("---------------------------");


        System.out.println("First word: " + binaryTree.getMin());
        System.out.println("Last word: " + binaryTree.getMax());
        System.out.println("---------------------------");

    }

    public static void menu(){

        Scanner scanner = new Scanner(System.in);
        String input;

        while(true){
            System.out.println("Type a word (or \"q\" for quit):");
            input = scanner.nextLine().toLowerCase();
            if (input.equals("q")){
                break;
            }
            String found = binaryTree.find(input);
            if (found != null){
                System.out.println("The word is found: " + found);
            } else {
                System.out.println("The word is not found. Here's the suggestions: ");
                printSuggestions(input);
            }
        }
    }

    public static void printSuggestions(String input){

        ArrayList<String> suggestions = new ArrayList<String>();

        //1. neighbor letter switching
        for (int i=0; i<input.length()-1; i++){
            char[] input_array = input.toCharArray();
            char tmp = input_array[i];
            input_array[i] = input_array[i+1];
            input_array[i+1] = tmp;

            addSuggestion(suggestions, input_array);
        }

        //2. one letter replacement
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        for (int i=0; i<input.length(); i++){
            for (char letter: alphabet) {
                char[] input_array = input.toCharArray();
                input_array[i] = letter;
                addSuggestion(suggestions, input_array);
            }
        }

        //3. letter adding
        char[] input_array = input.toCharArray();
        for (int i=0; i<input.length(); i++){
            for (char letter: alphabet) {
                char[] suggestion = copyArrayWithPlaceholder(input_array, i);
                suggestion[i] = letter;
                addSuggestion(suggestions, suggestion);
            }
        }



        //print out
        for (String item: suggestions) {
            System.out.println(item);
        }




    }

    private static void addSuggestion(ArrayList<String> suggestions, char[] combination){

        String suggestion = binaryTree.find(new String(combination));
        if (suggestion != null
                && (suggestions.indexOf(suggestion) == -1)){
            suggestions.add(suggestion);
        }

    }


    private static char[] copyArrayWithPlaceholder(char[] source, int indexPlaceholder){
        char[] result = new char[source.length + 1];
        for(int k=0; k<source.length + 1; k++){
            if(k==indexPlaceholder){
                continue;
            }
            result[k] = source[( k<indexPlaceholder ? k : k-1)];
        }
        return result;
    }

    public static void loadDictionary(String filename) throws Exception {

        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            binaryTree.insert(new BinaryNode(scanner.nextLine()));
        }
    }
}
