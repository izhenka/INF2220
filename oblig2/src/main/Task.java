package main;


import java.util.ArrayList;
import java.util.List;

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
