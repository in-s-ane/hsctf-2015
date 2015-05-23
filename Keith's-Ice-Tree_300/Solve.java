import java.util.*;
import java.io.*;
import java.lang.Math;

public class Solve {
    public static class Node {
        public int id;
        public int parent;
        public int lChild;
        public int rChild;
        public long numSubtrees;
        public Node(int _id, int _parent, int _lChild, int _rChild) {
            id = _id;
            parent = _parent;
            lChild = _lChild;
            rChild = _rChild;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        String data = in.readLine();
        int numNodes = Integer.parseInt(data);
        System.out.println("Num nodes: " + numNodes);
        Node[] nodes = new Node[numNodes];
        for (int i = 0; i < numNodes; ++i) {
            nodes[i] = new Node(i+1, 0, 0, 0);
        }
        for (int i = 0; i < numNodes; ++i) {
            String line = in.readLine();
            String[] lineData = line.split(" ");
            int p = Integer.parseInt(lineData[0]);
            int l = Integer.parseInt(lineData[1]);
            int r = Integer.parseInt(lineData[2]);
            if (l != 0) {
                nodes[p-1].lChild = l;
                nodes[l-1].parent = p;
            }
            if (r != 0) {
                nodes[p-1].rChild = r;
                nodes[r-1].parent = p;
            }
            nodes[p-1].numSubtrees = 1;
        }
        int rootNode = 0;
        for (int i = 0; i < numNodes; ++i) {
            if (nodes[i].parent == 0) {
                rootNode = i+1;
                System.out.println("Found root: " + Integer.toString(i+1));
            }
        }
        int depth = 0;
        for (int i = 0; i < numNodes; ++i) {
            Node curr = nodes[i];
            if (curr.lChild == 0 && curr.rChild == 0) { // Leaf
                int nodeDepth = 0;
                while (curr.parent != 0) { // While it's not the root node
                    curr = nodes[curr.parent-1];
                    ++nodeDepth;
                }
                depth = Math.max(depth, nodeDepth);
            }
        }
        System.out.println("Tree depth: " + Integer.toString(depth));
        //for (int passes = 0; passes < numNodes; ++passes) {
        //    for (int i = 0; i < numNodes; ++i) {
        //        Node curr = nodes[i];
        //        curr.numSubtrees = 1;
        //        if (curr.lChild != 0) {
        //            curr.numSubtrees += 1;
        //            curr.numSubtrees += nodes[curr.lChild-1].numSubtrees;
        //        }
        //        if (curr.rChild != 0) {
        //            curr.numSubtrees += 1;
        //            curr.numSubtrees += nodes[curr.rChild-1].numSubtrees;
        //        }
        //        if (curr.lChild != 0 && curr.rChild != 0) {
        //            curr.numSubtrees += 1;
        //        }
        //    }
        //}
        //System.out.println("Subtrees of root: " + Long.toString(nodes[rootNode-1].numSubtrees));
        //System.out.println("Subtrees of root.left: " + Long.toString(nodes[nodes[rootNode-1].lChild-1].numSubtrees));
        //System.out.println("Subtrees of root.right: " + Long.toString(nodes[nodes[rootNode-1].rChild-1].numSubtrees));
        ////long ans = 1;
        ////for (int i = 0; i < numNodes; ++i) {
        ////    //System.out.println(Long.toString(nodes[i].numSubtrees));
        ////    ans *= nodes[i].numSubtrees;
        ////    ans %= 1000000007;
        ////}
        ////System.out.println("Total: " + Long.toString(ans));
    }
}

