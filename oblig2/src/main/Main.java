package main;

public class Main {

    public static void main(String[] args) throws Exception{

        ProjectPlanner pp = new ProjectPlanner();
        try {
            pp.loadProject("buildhouse1.txt");
        } catch (Exception e) {
            System.out.println("The project hasn't been loaded :(");
            e.printStackTrace();
            return;
        }
        pp.fillOutEdges();

        if (!pp.isRealizable()){
            System.out.println("Sorry, project is not realizable :(");
            return;
        }else{
            System.out.println("Yeeey, project is realizable!");
        }

        //plan
        System.out.println("\n"+pp.toString());



    }
}
