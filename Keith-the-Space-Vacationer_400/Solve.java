import java.util.*;
import java.io.*;
import java.lang.Math;

public class Solve {
    public static class Edge {
        public final Node from;
        public final int dist;
        public final boolean requireToken;
        public Edge(Node _from, int _dist, boolean _requireToken) {
            from = _from;
            dist = _dist;
            requireToken = _requireToken;
        }
    }
    public static class Node {
        public int id;
        public int shortestWithToken;
        public int shortestWithoutToken;
        public ArrayList<Edge> neighbors; // previous nodes
        public Node(int _id) {
            id = _id;
            neighbors = new ArrayList<Edge>();
        }
    }
    public static int UNREACHABLE = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        for (int num=1; num<=6; num++) {
            BufferedReader in = new BufferedReader(new FileReader("test_cases/vacationer_test" + num + ".txt"));
            String data = in.readLine();
            String[] info = data.split(" ");
            int numPlanets = Integer.parseInt(info[0]);
            Node[] nodes = new Node[numPlanets];
            for (int i = 0; i < numPlanets; ++i) {
                nodes[i] = (new Node(i));
            }
            for (int i = 0; i < Integer.parseInt(info[1]); ++i) {
                String line = in.readLine();
                String[] lineData = line.split(" ");
                Edge tmp = new Edge(nodes[Integer.parseInt(lineData[0])], Integer.parseInt(lineData[2]), Integer.parseInt(lineData[3]) == 1 ? true : false);
                nodes[Integer.parseInt(lineData[1])].neighbors.add(tmp);
            }
            // Initialize the first node
            nodes[0].shortestWithToken = 0;
            nodes[0].shortestWithoutToken = 0;
            for (int i = 1; i < numPlanets; ++i) {
                nodes[i].shortestWithToken = UNREACHABLE;
                nodes[i].shortestWithoutToken = UNREACHABLE;
            }

            //for (int i = 0; i < numPlanets; ++i) {
            //    System.out.println(Integer.toString(nodes[i].neighbors.size()));
            //}
            for (int passes = 0; passes < numPlanets; ++passes) {
                for (int i = 0; i < numPlanets; ++i) {
                    for (Edge e : nodes[i].neighbors) {
                        if (e.requireToken) {
                            // Use the token now
                            if (e.from.shortestWithoutToken != UNREACHABLE) {
                                nodes[i].shortestWithToken = Math.min(nodes[i].shortestWithToken, e.dist + e.from.shortestWithoutToken);
                            }
                        }
                        else {
                            // Case when the token has been previously used
                            if (e.from.shortestWithToken != UNREACHABLE) {
                                nodes[i].shortestWithToken = Math.min(nodes[i].shortestWithToken, e.dist + e.from.shortestWithToken);
                            }
                            if (e.from.shortestWithoutToken != UNREACHABLE) {
                                nodes[i].shortestWithoutToken = Math.min(nodes[i].shortestWithoutToken, e.dist + e.from.shortestWithoutToken);
                            }
                        }
                    }
                }
            }
            System.out.println(Math.min(nodes[numPlanets-1].shortestWithToken, nodes[numPlanets-1].shortestWithoutToken));
        }
        // The flag is: 34;15;26;40;30;31
    }
}

