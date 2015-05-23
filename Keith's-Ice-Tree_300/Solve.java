import java.util.*;
import java.io.*;

public class Solve {

    public static Node[] nodeList;

    public static class Node {
        int score;
        int number;
        int parent;
        int left;
        int right;
        boolean checked = false;
        public Node(int number) {
            this.number = number;
        }
        public String toString() {
            return this.left + "-<" + this.number + "(" + score + ")>-" + this.right;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        String data = in.readLine();
        int numNodes = Integer.parseInt(data);
        nodeList = new Node[numNodes+1];
        for (int i = 1; i <= numNodes; i++) {
            nodeList[i] = (new Node(i));
        }
        for (int i = 1; i <= numNodes; i++) {
            String line = in.readLine();
            String[] nodeData = line.split(" ");
            int number = Integer.parseInt(nodeData[0]);
            int left = Integer.parseInt(nodeData[1]);
            int right = Integer.parseInt(nodeData[2]);
            nodeList[number].left = left;
            nodeList[number].right = right;
            if (left != 0) {
                nodeList[left].parent = number;
            }
            if (right != 0) {
                nodeList[right].parent = number;
            }
        }
        long answer = scoreNodes(nodeList);
        System.out.println("Total number of connected subtrees: " + answer);
        System.out.println("Total number of connected subtrees mod 1000000007: " + answer % 1000000007);
        //System.out.println(Arrays.toString(nodeList));
    }

    public static long scoreNodes(Node[] nodeList) {
        ArrayList<Node> temp = new ArrayList<Node>();
        for (int i=1; i<nodeList.length; i++) {
            if (nodeList[i].left == 0 && nodeList[i].right == 0) {
                temp.add(nodeList[i]);
            }
        }
        while (!temp.isEmpty()) {
            int score = 1;
            Node cur = temp.remove(0);
            if (cur != null) {
                if ((cur.left != 0 && !nodeList[cur.left].checked) || (cur.right != 0 && !nodeList[cur.right].checked)) {
                    temp.add(cur);
                    continue;
                }
                if (cur.left != 0 && cur.right != 0) {
                    score += nodeList[cur.left].score * nodeList[cur.right].score;
                }
                if (cur.left != 0) {
                    score += nodeList[cur.left].score;
                }
                if (cur.right != 0) {
                    score += nodeList[cur.right].score;
                }
                cur.score = score;
                cur.checked = true;
                if (!temp.contains(nodeList[cur.parent])) {
                    temp.add(nodeList[cur.parent]);
                }
            }
        }
        long total = 0;
        for (int i=1; i<nodeList.length; i++) {
            total += nodeList[i].score;
        }
        return total;
    }

}
