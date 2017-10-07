package main;


import java.util.List;

public class Task {
    int id, time, staff;
    String name;
    int earliestStart, latestStart;
    List<Task> outEdges;
    int cntPredecessors;
    List<Integer> outEdgesId;



    public Task(int id, int time, int staff, String name, List<Integer> outEdgesId) {
        this.id = id;
        this.time = time;
        this.staff = staff;
        this.name = name;
        this.outEdgesId = outEdgesId;
    }

    @Override
    public String toString() {
        return "\nTask{" +
                "id=" + id +
                ", time=" + time +
                ", staff=" + staff +
                ", name='" + name + '\'' +
                ", earliestStart=" + earliestStart +
                ", latestStart=" + latestStart +
                ", outEdges=" + outEdges +
                ", cntPredecessors=" + cntPredecessors +
                ", outEdgesId=" + outEdgesId +
                '}';
    }
}
