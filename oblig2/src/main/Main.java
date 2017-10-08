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
        System.out.println(pp.toString());



    }
}
