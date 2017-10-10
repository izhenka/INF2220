package main;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

public class Task {
    int id, time, staff;
    String name;
    int earliestStart = -1, latestStart = -1;
    List<Task> outEdges = new ArrayList<Task>();
    List<Task> inEdges = new ArrayList<Task>();
    int cntPredecessors;
    List<Integer> predecessorsId;
    Task criticalPredecessor;

    int distance;
    Task predecessor;



    public Task(int id, int time, int staff, String name, List<Integer> predecessorsId) {
        this.id = id;
        this.time = time;
        this.staff = staff;
        this.name = name;
        this.predecessorsId = predecessorsId;
        this.outEdges = new ArrayList<Task>();
        this.inEdges = new ArrayList<Task>();

        cntPredecessors = predecessorsId.size();
    }

    public List<Integer> findCyclePath(){

        for (Task outT:outEdges) {
            List<Integer> path = new LinkedList<Integer>();
            path.add(this.id);

            if(isCycle(this, outT, path)){
                return path;
            }
        }
        return null;
    }

    private boolean isCycle(Task start, Task current, List<Integer> path){
        path.add(current.id);
        if (current == start){
            return true;
        } else {
            for (Task outT: current.outEdges) {
                if(isCycle(start, outT, path)){
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public String toString() {

        String outEdges_str = "";
        for (Task t: outEdges) {
            outEdges_str += t.id + "-" + t.name + ", ";
        }
        if(!outEdges.isEmpty()){
            outEdges_str = outEdges_str.substring(0, outEdges_str.length()-2);
        } else {
            outEdges_str = "None";
        }


        return "\nTask " + id + ":" +
                "\n\tid: " + id +
                "\n\tname: " + name +
                "\n\ttime needed to finish the task: " + time +
                "\n\tmanpower: " + staff +
                "\n\tearliest starting time: " + earliestStart +
                "\n\tlatest starting time: " + latestStart +
                "\n\tslack: " + (latestStart - earliestStart) +
                "\n\ttasks depending on this task: " + outEdges_str;



    }
}
