package maze;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class MazeNode extends Node {

    public State state;
    Node parent;
    Double g;
    //HashMap map;

    public MazeNode(Node parent, State state) {
        this.parent = parent;
        this.state = state;
        if (parent == null) {
            this.g = 0.0;
        } else {
            if(this.parent.g != null){
                this.g = (Double) (this.parent.g + 1);
            }else{
                this.g = 1.0;
            }
            
        }
    }

    @Override
    public List<Node> expandNode() {
        List<Node> expandnodelist = new ArrayList<>();
        for (State s : state.expand()) {
            expandnodelist.add(new MazeNode(this, s));
        }
        return expandnodelist;
    }
    
    @Override
    public Double g() {
        return g;
    }

    @Override
    public Double h() {
        return state.distFromGoal();
    }

    @Override
    public boolean isGoal() {
        return state.isGoal();
    }

    @Override
    public int getX() {
        return state.point.x;
    }

    @Override
    public int getY() {
        return state.point.y;
    }

    @Override
    public Maze getMaze() {
        return state.maze;
    }

    @Override
    public Double cust() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public void getPath() {
         if (parent != null) {
            ((MazeNode) parent).getPath();
        }
        state.maze.addPoint(state.point.x, state.point.y);
        try {
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println("");
            state.maze.display();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(MazeNode.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Thread.sleep(215);
    }

    @Override
    public String toString() {
        return state.toString();
    }

}
