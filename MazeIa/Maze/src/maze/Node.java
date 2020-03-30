package maze;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Node {
     //custo-peso
     Double g;
     public abstract Double g();
     public abstract Double h();
     public abstract boolean isGoal();
     public abstract int getX();
     public abstract int getY();
     public abstract String toString();
     //public abstract Maze getMaze();
    //public abstract List<State> successors();
    public abstract Double cust();
    public abstract Maze getMaze();

     
    // Boolean isGoal;
     Collection<Node>expand;

    public abstract List<Node> expandNode();
    public abstract void getPath();




    
}
