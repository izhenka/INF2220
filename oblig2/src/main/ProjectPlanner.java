package main;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ProjectPlanner {

    public  Task[] tasks;

    public void loadProject(String fileName) throws Exception{

        File file = new File(fileName);
        Scanner scanner = new Scanner(file);


        int numberTasks = Integer.parseInt(scanner.nextLine().trim());
        tasks = new Task[numberTasks];

        int cnt = 1;
        scanner.nextLine();
        while (scanner.hasNextLine() && cnt<=numberTasks) {
            String line = scanner.nextLine().trim();
            String[] parameters = line.split("\\s+");
            int id = Integer.parseInt(parameters[0]);
            String name = parameters[1];
            int time = Integer.parseInt(parameters[2]);
            int manpower = Integer.parseInt(parameters[3]);

            List<Integer> outEdgesId = new ArrayList<Integer>();
            for (int i = 4; i < parameters.length-1; i++){
                outEdgesId.add(Integer.parseInt(parameters[i]));
            }
            tasks[id-1] = new Task(id, time, manpower, name, outEdgesId);
            cnt++;
        }
        //fill outEdges with Task references
        for (Task t:tasks) {
            for (int outEdgeId: t.outEdgesId) {
                t.outEdges.add(tasks[outEdgeId-1]);
            }
        }
        System.out.println("Project is loaded!");
    }

    @Override
    public String toString() {
        return "ProjectPlanner{" +
                "tasks=" + Arrays.asList(tasks) +
                '}';
    }
}