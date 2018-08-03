package algorithms;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import model.Treasure;
import model.Pirate;
import model.Maze;

public class AStarSearchEngine extends AbstractSearchEngine {
    
    ArrayList<Node> openList;
    ArrayList<Node> closedList;
    Node goal;
    Node start;

    public AStarSearchEngine(Maze maze) {
        super(maze);
    }

    public synchronized Dimension[] getSearchPath(Pirate bot, Treasure bonus) {
        openList = new ArrayList<>();
        closedList = new ArrayList<>();
        goal = new Node(bonus.getPosition().width, bonus.getPosition().height);
        start = new Node(bot.getPosition().width, bot.getPosition().height);
        initSearch(bot, bonus);
        ArrayList<Node> lino = doAStar();
        Dimension[] dim = new Dimension[lino.size()];
        int i = 0;
        if (!lino.isEmpty()) {
            for (Node n : lino) {
                dim[i] = new Dimension(n.getX(), n.getY());
                i++;
            }
        } else {
            dim = null;
        }
        return dim;
    }

    private synchronized Node lowestFInList(List<Node> list) {
        Node cheapest = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getF() < cheapest.getF()) {
                cheapest = list.get(i);
            }
        }
        return cheapest;
    }

    private ArrayList<Node> calcPath(Node start, Node goal) {
        ArrayList<Node> path = new ArrayList<Node>();

        Node node = goal;
        boolean done = false;
        while (!done) {
            path.add(node);
            node = node.getParent();
            if (node.equals(start)) {
                done = true;
            }
        }
        path.add(start);
        return path;
    }

    public ArrayList<Node> doAStar() {

        if (start.getX() == goal.getX() && start.getY() == goal.getY()) {
            // Return an empty path, because we don't need to move at all.
            return new ArrayList<Node>();
        }

        openList.add(start);

        while (true) {
            // Gets node with the lowest F score from open list.
            Node current = lowestFInList(openList);
            // Remove current node from open list.
            openList.remove(current);
            // Add current node to closed list.
            closedList.add(current);

            // If the current node position is equal to the goal position ...
            if ((current.getX() == goal.getX()) && (current.getY() == goal.getY())) {
                // Return a LinkedList containing all of the visited nodes.
                return calcPath(start, current);
            }

            ArrayList<Node> adjacentNodes = new ArrayList<Node>();
            Dimension[] connected = getPossibleMoves(new Dimension(current.getX(), current.getY()));
            for (int i = 0; i < connected.length; i++) {
                if (connected[i] != null) {
                    Node cd = new Node(connected[i].width, connected[i].height);
                    adjacentNodes.add(cd);
                } else {
                    //
                }
            }
            for (Node adjacent : adjacentNodes) {
                // If node is not in the open list ...
                if (!openList.contains(adjacent)) {
                    // Set current node as parent for this node.
                    adjacent.setParent(current);
                    // Set H costs of this node (estimated costs to goal).
                    adjacent.setH(goal);
                    // Set G costs of this node (costs from start to this node).
                    adjacent.setG(current);
                    // Add node to openList.
                    openList.add(adjacent);
                } // Else if the node is in the open list and the G score from
                // current node is cheaper than previous costs ...
                else if (adjacent.getG() > adjacent.calculateG(current)) {
                    // Set current node as parent for this node.
                    adjacent.setParent(current);
                    // Set G costs of this node (costs from start to this node).
                    adjacent.setG(current);
                }
            }

            if (openList.isEmpty()) {
                // Return an empty list.
                return new ArrayList<Node>();
            }
        }
    }

}
