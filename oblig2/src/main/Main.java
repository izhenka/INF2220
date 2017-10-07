package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        ProjectPlanner pp = new ProjectPlanner();
        try {
            pp.loadProject("buildhouse1.txt");
        } catch (Exception e) {
            System.out.println("The project hasn't been loaded :( \n" + e.getMessage());
            return;
        }
        System.out.println(pp.toString());



    }
}
