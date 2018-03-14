/*
* @Author: Chris Kim, Thinh Luu
* @Usernames: ckim65, tpluu
* @Date:   2018-03-01 16:40:35
* @Last Modified by:   tpluu
* @Last Modified time: 2018-03-02 14:49:11
*/

import java.util.*;

public class DiGraph {

    private LinkedList<Integer> [] linkedListArray;

    public DiGraph(int n) {

        //creating array of linkedlist
        linkedListArray = new LinkedList[n];

        //initializing array
        for(int i = 0; i < n; i++){
            linkedListArray[i] = new LinkedList<Integer>();
        }
    }

    public void addEdge(int from, int to) {

        int naturalFrom = from-1;

        LinkedList<Integer> wantedVertex = linkedListArray[naturalFrom];

        if(!(wantedVertex.contains(to))) {
            wantedVertex.add(to);
        }
    }

    public void deleteEdge(int from, int to) {

        int naturalFrom = from-1;

        LinkedList<Integer> wantedVertex = linkedListArray[naturalFrom];

        if(wantedVertex.contains(to)) {
            wantedVertex.removeFirstOccurrence(to);
        }
    }

    public int edgeCount() {

        int edgeCount = 0;

        for(int i = 0; i < linkedListArray.length; i++) {
            edgeCount += linkedListArray[i].size();
        }

        return edgeCount;
    }   

    public int vertexCount() {

        return linkedListArray.length;
    }

    public void print() {

        for(int i = 0; i < linkedListArray.length; i++) {
            System.out.print((i+1) + " is connected to: ");

            for (int j = 0; j < linkedListArray[i].size(); j++) {

                if(j == linkedListArray[i].size()-1) {
                    System.out.print(linkedListArray[i].get(j));
                }
                else {
                    System.out.print(linkedListArray[i].get(j) + ",");
                }
            }
            System.out.println();
        }
    }

    public int[] topSort() {

        int N = linkedListArray.length;
        int[] IN = indegrees();
        int[] A = new int[N];
        Integer U;
        Queue<Integer> q = new LinkedList<Integer>();

        for(int u = 0; u < N; u++) {
            if(IN[u] == 0) {
                U = new Integer(u);
                q.add(U);
            }
        }

        int k = 0;

        while(q.size() != 0) {

            //remove returns Integer, casting back to primitive int
            Integer W = q.remove();
            int w = W.intValue();

            A[k] = w;
            k+=1;

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < linkedListArray[i].size(); j++) {
                    int node = linkedListArray[i].get(j);
                    IN[node-1] -=1;
                    if(IN[node-1] == 0) {
                        Integer NODE = new Integer(node-1);
                        q.add(NODE);
                    }
                }
            }
        }

        if((N) != k) {
            throw new IllegalArgumentException();
        }
        return A;
    }

    private int[] indegrees() {

        int numVertices = linkedListArray.length;
        int[] IN = new int[numVertices];

        for(int i = 0; i < numVertices; i++) {
            for(int j = 0; j < linkedListArray[i].size(); j++) {
                int node = linkedListArray[i].get(j);
                IN[node-1] +=1;
            }
        }

        return IN;
    }

    private class VertexInfo {

        int distance;
        int parent;

        public VertexInfo (int distance, int parent) {
            this.distance = distance;
            this.parent = parent;
        }

    }

    private VertexInfo[] BFS(int s) {

        int lenOfGraph = linkedListArray.length;
        VertexInfo[] result = new VertexInfo[lenOfGraph];

        for (int i = 0; i < lenOfGraph; i++) {

            result[i] = new VertexInfo(-1, -1);
            

        }

        result[s].distance = 0;

        Queue<Integer> q = new LinkedList<Integer>();

        q.add(new Integer(s));

        while(q.size() > 0) {
            Integer u = q.remove();
            for(int j = 0; j < linkedListArray[u].size(); j++) {
                int node = linkedListArray[u].get(j) - 1;

                if (result[node].distance == -1) {
                    result[node].distance = result[u].distance + 1;
                    result[node].parent = u;
                    q.add(new Integer(node));
                }

            }

        }
        return result;
    }

    public boolean isTherePath(int from, int to) {

        from -= 1;
        to -= 1;

        VertexInfo[] results = BFS(from);
        if (results[to].distance != -1) {
            return true;
        }
        else {
            return false;
        }


    }

    public int lengthOfPath(int from, int to) {
        from -= 1;
        to -= 1;

        VertexInfo[] results = BFS(from);

        return results[to].distance;

    }

    public void printPath(int from, int to) {

        from -= 1;
        to -= 1;

        if (isTherePath(from+1, to+1)) {

            VertexInfo[] results = BFS(from);
            int i = to;
            String path = "";
            while (results[i].parent != -1) {

                path = "->"+ (i + 1) + path; 
                i = results[i].parent;
            }
            System.out.println("" + (i + 1) + path);  
        }
        else {
            System.out.println("There is no path");
        }

    }

    private class TreeNode{

        int vertexNum;
        LinkedList<TreeNode> list;

        public TreeNode(int vertexNum, LinkedList<TreeNode> list) {

            this.vertexNum = vertexNum;
            this.list = list;
        }
    }

    private TreeNode buildTree(int s) {

        VertexInfo[] results = BFS(s-1);
        TreeNode[] nodes = new TreeNode[linkedListArray.length];

        for(int i = 0; i < linkedListArray.length; i++) {

            nodes[i] = new TreeNode(i+1, new LinkedList<TreeNode>());

        }

        for (int i = 0; i < results.length; i++) {

            if (results[i].parent != -1) {

                nodes[results[i].parent].list.add(nodes[i]);

            }

        }

        return nodes[s-1];

    }

    public void printTree(int s) {

        TreeNode root = buildTree(s);
        recursiveprint(root, "");


    }

    private void recursiveprint(TreeNode s, String tabs){

        System.out.println(tabs + s.vertexNum);
        if (s.list.size() > 0){
            for(int i = 0; i < s.list.size(); i++){
                recursiveprint(s.list.get(i), tabs + "    ");
            }
        }

    }
}