package main;

import java.util.Arrays;
import java.util.Collections;

public class Oblig2 {

    public static void main(String[] args) throws Exception{

        ProjectPlanner pp = new ProjectPlanner();
        try {
            //TODO change filename to app's argument
            //String filename = args[1];
            pp.loadProject("buildhouse1.txt");
            if (pp.tasks.length == 0){
                System.out.println("Nothing to plan");
                return;
            }
        } catch (Exception e) {
            System.out.println("The project hasn't been loaded :(");
            e.printStackTrace();
            return;
        }


        pp.fillInOutEdges();

        if (!pp.isRealizable()){
            System.out.println("The project is not realizable :(");
            return;
        }else{
            System.out.println("Yeeey, project is realizable!");
        }

        pp.resetTaskPrececcorsCounter();
        pp.computeSchedule();
        pp.printOutSimulation();


        //plan
        System.out.println("\nAll tasks:");
        for (Task t:pp.tasks) {
            System.out.println(t);
        }



    }
}
