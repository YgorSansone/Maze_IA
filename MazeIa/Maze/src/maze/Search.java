package maze;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Search {

    enum SearchType {
        bf, df, astar
    };

    SearchType Type;
    Collection<Node> Fringe[];
    HashMap<Point, Boolean> map;

//    enquanto fringe não tá vazio
//    :
//  n  = remove do fringe se n é goal
//    :
//    retorna n
//    se n ainda não foi aberto
//    :
//    abre n
//    expand n e adiciona todos no fringe repeat
    public Node search(Node root, int b) throws InterruptedException {
        //1 DFS
        //2 BFS
        //3 A*
        this.map = new HashMap();
        Node node = null;
        Collection<Node> fringe;
        if (b == 1 || b == 2) {
            fringe = new LinkedList<>();
        } else {
            fringe = new TreeSet<Node>(new Comparador());
        }
        if (b == 1) {
            System.out.println("DFS");
        } else {
            System.out.println("BFS");
        }
        fringe.add(root);

        while (!fringe.isEmpty()) {
            if (b == 1 || b == 2) {
                node = ((List<Node>) fringe).remove(0);
            } else {
                node = ((TreeSet<Node>) fringe).pollFirst();
            }

            if (node.isGoal()) {
                
                node.getPath();
                
                return node;

            } else {
                if (discovered(((MazeNode) node).state) == false) {
                    discover(((MazeNode) node).state);
                    List<Node> temp = node.expandNode();

                    temp.forEach((a) -> {
                        if (!discovered(((MazeNode) a).state)) {
                            if (b == 1) {
                                fringe.add(a);
                            } else if (b == 2) {
                                ((List<Node>) fringe).add(0, a);
                            }else{
                                ((TreeSet<Node>) fringe).add(a);
                            }
                        }
                    });

                    //fringe.addAll(temp);
                    //System.out.println((fringe.size() - 1));
                }
            }
        };

        return node;
    }

    class Comparador implements Comparator<Node> {

        @Override
        public int compare(Node no1, Node no2) {
            Double a = no1.g() + no1.h();
            Double b = no2.g() + no2.h();
            if (a > b) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    public boolean discovered(State state) {
        //System.out.println(this.map);
        boolean a;
        if (this.map.get(state.point) != null) {
            a = true;
        } else {
            a = false;
        }
        return a;
    }

    public void discover(State state) {
        this.map.put(state.point, true);
    }
}
