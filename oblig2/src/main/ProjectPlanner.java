package main;


import java.io.File;
import java.util.*;

public class ProjectPlanner {

    public  Task[] tasks;
    public List<Task> topSortedTasks;

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

            List<Integer> predecessorsId = new ArrayList<Integer>();
            for (int i = 4; i < parameters.length-1; i++){
                predecessorsId.add(Integer.parseInt(parameters[i]));
            }
            tasks[id-1] = new Task(id, time, manpower, name, predecessorsId);
            cnt++;
        }
        System.out.println("Project is loaded!");
    }


    public void fillOutEdges(){
        for (Task task:tasks) {
            for (int predecessorId: task.predecessorsId) {
                Task predecessor = tasks[predecessorId-1];
                predecessor.outEdges.add(task);
            }
        }
    }


    public Boolean isRealizable(){

        topSortedTasks = new ArrayList<Task>();
        Queue<Task> q = new LinkedList<Task>();
        int cnt = 0;

        for (Task task:tasks) {
            if (task.cntPredecessors == 0){
                q.add(task);
            }
        }
        while(!q.isEmpty()){
            Task t = q.remove();
            topSortedTasks.add(t);
            cnt++;
            for (Task adjacentT: t.outEdges) {
                if(--adjacentT.cntPredecessors == 0){
                    q.add(adjacentT);
                }
            }
        }
        if (cnt < tasks.length){
            printOutCycle();
            return false;
        }
        return true;
    }

    private void printOutCycle(){
        for (Task t: tasks) {
            if(t.cntPredecessors>0){
                List<Integer> cyclePath = t.findCyclePath();
                if(cyclePath != null){
                    System.out.println("There is a cycle in project.\nCycle path:" + cyclePath);
                       return;
                }
            }
        }
    }

    public int findCompletionTime(){

        topSortedTasks.get(0).earliestStart = 0;
        for (Task t: topSortedTasks) {
            for (Task adjacent_t: t.outEdges) {
                if(t.earliestStart + t.time >adjacent_t.earliestStart){
                    adjacent_t.earliestStart = t.earliestStart + t.time;
                    adjacent_t.criticalPredecessor = t;
                }
            }
        }

        return 0;
    }

    @Override
    public String toString() {
        return "ProjectPlanner{" +
                "tasks=" + Arrays.asList(tasks) +
                '}';
    }
}
