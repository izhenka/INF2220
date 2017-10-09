package main;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

public class Task {
    int id, time, staff;
    String name;
    int earliestStart, latestStart;
    List<Task> outEdges = new ArrayList<Task>();
    int cntPredecessors;
    List<Integer> predecessorsId;



    public Task(int id, int time, int staff, String name, List<Integer> predecessorsId) {
        this.id = id;
        this.time = time;
        this.staff = staff;
        this.name = name;
        this.predecessorsId = predecessorsId;
        this.outEdges = new ArrayList<Task>();
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
//        System.out.println("start: " + start.id + "\tcurrent: " + current.id);
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
        }


        return "\nTask{" +
                "id=" + id +
                ", time=" + time +
                ", staff=" + staff +
                ", name='" + name + '\'' +
                ", earliestStart=" + earliestStart +
                ", latestStart=" + latestStart +
                ", outEdges=" + outEdges_str +
                ", cntPredecessors=" + cntPredecessors +
                ", predecessorsId=" + predecessorsId +
                '}';
    }
}
