package main;

public class Main {

    public static void main(String[] args) throws Exception{

        ProjectPlanner pp = new ProjectPlanner();
        try {
            //TODO change filename to app's argument
            //String filename = args[1];
            pp.loadProject("buildhouse2.txt");
        } catch (Exception e) {
            System.out.println("The project hasn't been loaded :(");
            e.printStackTrace();
            return;
        }
        pp.fillOutEdges();

        if (!pp.isRealizable()){
            System.out.println("Sorry, the project is not realizable :(");
            return;
        }else{
            System.out.println("Yeeey, project is realizable!");
        }

        //plan
        System.out.println("\n"+pp.toString());



    }
}
