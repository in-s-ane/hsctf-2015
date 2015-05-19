import java.util.*;
import java.io.*;


public class solve {

    public static class Node {
        Node parent = null;
        int x, y;
        double f, g, h;
        char element;
        public Node(int y, int x) {
            this.x = x;
            this.y = y;
        }
        public String toString() {
            return String.valueOf(element) + "(" + x + "," + y + ")";
        }
    }

    public static double distance(Node node0, Node node1) {
        int x0 = node0.x;
        int y0 = node0.y;
        int x1 = node1.x;
        int y1 = node1.y;
        return Math.sqrt((x0-x1)*(x0-x1) + (y0-y1)*(y0-y1));
    }

    public static boolean isGood(Node check, ArrayList<Node> list) {
        for (Node n : list) {
            if (check.x == n.x && check.y == n.y) {
                return check.f < n.f;
            }
        }
        return true;
    }

    public static Node findLeastF(ArrayList<Node> list) {
        Node lowest = list.get(0);
        for (Node n : list) {
            if (lowest.f > n.f) {
                lowest = n;
            }
        }
        return lowest;
    }

    public static void main(String[] args) throws IOException {
        for (int num=1; num<=6; num++) {
            BufferedReader in = new BufferedReader(new FileReader("test_cases/track_star_test" + num + ".txt"));
            //BufferedReader in = new BufferedReader(new FileReader("test.txt"));
            String data = in.readLine();
            String[] info = data.split(" ");
            Node[][] grid = new Node[Integer.parseInt(info[0])][Integer.parseInt(info[1])];
            for (int i=0; i<grid.length; i++) {
                for (int j=0; j<grid[0].length; j++) {
                    grid[i][j] = new Node(i, j);
                    grid[i][j].element = '.';
                }
            }
            for (int j=0; j<Integer.parseInt(info[2]); j++) {
                String hurdle = in.readLine();
                String coordinates[] = hurdle.split(" ");
                grid[Integer.parseInt(coordinates[0])][Integer.parseInt(coordinates[1])].element = 'x';
            }
            ArrayList<Node> solution = runHurdles(grid.length, grid[0].length, grid);
            //System.out.println(solution);
            int swaps = 0;
            for (int s=1; s<solution.size(); s++) {
                if (solution.get(s-1).x != solution.get(s).x) {
                    swaps++;
                }
            }
            System.out.println(swaps);
        }
    }

    public static ArrayList<Node> reconstructPath(Node end) {
        ArrayList<Node> solution = new ArrayList<Node>();
        solution.add(end);
        while (end.parent != null) {
            solution.add(end.parent);
            end = end.parent;
        }
        Collections.reverse(solution);
        return solution;
    }

    public static ArrayList<Node> runHurdles(int rows, int cols, Node[][] grid) {
        /*
        for (Node[] x : grid) {
            System.out.println(Arrays.toString(x));
        }
        */
        ArrayList<Node> openList = new ArrayList<Node>();
        ArrayList<Node> closedList = new ArrayList<Node>();
        for (int x=0; x<cols; x++) {
            openList.add(grid[0][x]); // Can start from any of the top rows
        }
        while (openList.size() != 0) {
            Node leastNodeF = findLeastF(openList);
            if (leastNodeF.y == rows-1) {
                return reconstructPath(leastNodeF);
            }

            openList.remove(leastNodeF);
            closedList.add(leastNodeF);
            ArrayList<Node> neighbors = new ArrayList<Node>();
            // We can only go downwards
            if (grid[leastNodeF.y+1][leastNodeF.x].element == '.') {
                neighbors.add(grid[leastNodeF.y+1][leastNodeF.x]);
            }
            if (leastNodeF.x-1 >= 0) {
                if (grid[leastNodeF.y+1][leastNodeF.x-1].element == '.') {
                    neighbors.add(grid[leastNodeF.y+1][leastNodeF.x-1]);
                }
            }
            if (leastNodeF.x+1 < cols) {
                if (grid[leastNodeF.y+1][leastNodeF.x+1].element == '.') {
                    neighbors.add(grid[leastNodeF.y+1][leastNodeF.x+1]);
                }
            }

            for (Node neighbor : neighbors) {
                if (closedList.contains(neighbor)) {
                    continue;
                }
                double gTemp = leastNodeF.g + distance(leastNodeF, neighbor);
                if (!openList.contains(neighbor) || gTemp < neighbor.g) {
                    neighbor.parent = leastNodeF;
                    neighbor.g = gTemp;
                    neighbor.h = (neighbor.y-cols-1)*(neighbor.y-cols-1);//distance(neighbor, grid[rows-1][cols-1]);
                    neighbor.f = neighbor.g + neighbor.h;
                    if (!openList.contains(neighbor)) {
                        openList.add(neighbor);
                    }
                }
            }
        }
        return null;
    }
}

