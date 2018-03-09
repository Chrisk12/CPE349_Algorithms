/*
* @Author: thinhluu
* @Date:   2018-03-05 18:21:13
* @Last Modified by:   tpluu
* @Last Modified time: 2018-03-08 23:08:16
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
}