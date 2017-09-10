package main;

import java.io.File;
import java.util.ArrayList;
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
        System.out.println("Lasr word: " + binaryTree.getMax());
        System.out.println("---------------------------");

    }

    public static void menu(){

        Scanner scanner = new Scanner(System.in);
        String input = "";

        while(true){
            System.out.println("Type a word:");
            input = scanner.nextLine();
            if (input.equals("q")){
                break;
            }
            System.out.println(binaryTree.find(input));

        }
    }


    public static void loadDictionary(String filename) throws Exception {

        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            binaryTree.insert(new BinaryNode(scanner.nextLine()));
        }
    }
}
