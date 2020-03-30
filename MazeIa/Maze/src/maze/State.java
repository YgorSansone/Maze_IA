package maze;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class State {

    Maze maze;
    Point point;

    HashMap<String, Character> map;
    //List<Object> path = new ArrayList<>();

    public State(Maze maze, Point point) {
        this.maze = maze;
        this.point = point;

    }

    public boolean isGoal() {
        return distFromGoal() == 0;
    }

    public Double distFromGoal() {
        //return Double.valueOf(Math.abs(point.x - maze.getDestinationX()) + Math.abs(point.y - maze.getDestinationY()));
        return Point.distance(point.x, point.y, maze.getDestinationX(), maze.getDestinationY());
    }

    //Lista de nos vazias
    public List<State> expand() {
        List<State> expand = new ArrayList<>();
        //HashMap<String, Point> expandido = new HashMap<>();

        if (maze.isEmpty(this.point.x, this.point.y - 1)) {
            expand.add(new State(this.maze, new Point(this.point.x,this.point.y - 1)));
        }
        if (maze.isEmpty(this.point.x,this.point.y + 1)) {
            expand.add(new State(this.maze, new Point(this.point.x,this.point.y + 1)));
        }
        if (maze.isEmpty(this.point.x - 1, this.point.y)) {
            expand.add(new State(this.maze, new Point(this.point.x - 1, this.point.y)));
        }
        if (maze.isEmpty(this.point.x + 1,this.point.y)) {
            expand.add(new State(this.maze, new Point(this.point.x + 1,this.point.y)));
        }

        return expand;
    }

    @Override
    public String toString() {
        return point.x + "-" + point.y;
    }

}
