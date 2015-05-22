import java.util.*;
import java.io.*;
import java.lang.Math;

public class Solve {
    public static class Edge {
        public final Node from;
        public final int dist;
        public final int points;
        public Edge(Node _from, int _dist, int _points) {
            from = _from;
            dist = _dist;
            points = _points;
        }
    }
    public static class Node {
        public int id;
        public int shortestDist;
        public int pointValue;
        public ArrayList<Edge> neighbors; // previous nodes
        public Node(int _id) {
            id = _id;
            neighbors = new ArrayList<Edge>();
        }
    }
    public static int UNREACHABLE = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        String data = in.readLine();
        int numPlanets = Integer.parseInt(data);
        System.out.println("Num planets: " + numPlanets);
        Node[] nodes = new Node[numPlanets];
        for (int i = 0; i < numPlanets; ++i) {
            nodes[i] = (new Node(i));
        }
        int numEdges = Integer.parseInt(in.readLine());
        System.out.println("Num edges: " + numEdges);
        for (int i = 0; i < numEdges; ++i) {
            String line = in.readLine();
            String[] lineData = line.split(" ");
            Edge tmp = new Edge(nodes[Integer.parseInt(lineData[0])], Integer.parseInt(lineData[2]), Integer.parseInt(lineData[3]));
            nodes[Integer.parseInt(lineData[1])].neighbors.add(tmp);
            tmp = new Edge(nodes[Integer.parseInt(lineData[1])], Integer.parseInt(lineData[2]), Integer.parseInt(lineData[3]));
            nodes[Integer.parseInt(lineData[0])].neighbors.add(tmp);
        }
        // Initialize the first node
        nodes[0].shortestDist = 0;
        nodes[0].pointValue = 0;
        for (int i = 1; i < numPlanets; ++i) {
            nodes[i].shortestDist = UNREACHABLE;
            nodes[i].pointValue = Integer.MAX_VALUE;
        }

        //for (int i = 0; i < numPlanets; ++i) {
        //    System.out.println(Integer.toString(nodes[i].neighbors.size()));
        //}
        for (int passes = 0; passes < numPlanets; ++passes) {
            for (int i = 0; i < numPlanets; ++i) {
                for (Edge e : nodes[i].neighbors) {
                    if (e.from.shortestDist != UNREACHABLE) {
                        if (nodes[i].shortestDist > e.dist + e.from.shortestDist) {
                            nodes[i].shortestDist = e.dist + e.from.shortestDist;
                            nodes[i].pointValue = e.points + e.from.pointValue;
                        }
                        else if (nodes[i].shortestDist == e.dist + e.from.shortestDist) {
                            nodes[i].pointValue = Math.min(nodes[i].pointValue, e.points + e.from.pointValue);
                        }
                    }
                }
            }
        }
        System.out.println("Dist: " + Integer.toString(nodes[numPlanets - 1].shortestDist));
        System.out.println("Points: " + Integer.toString(nodes[numPlanets - 1].pointValue)); 
        // The flag is: 80 -4722
    }
}

