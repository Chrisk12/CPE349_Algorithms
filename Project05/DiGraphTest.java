/*
* @Author: tpluu
* @Date:   2018-03-06 18:28:48
* @Last Modified by:   tpluu
* @Last Modified time: 2018-03-08 23:10:04
*/

import java.util.Scanner;

public class DiGraphTest {

   public static void main(String[] args) {
       
        Scanner scan = new Scanner(System.in);

       System.out.print("Enter the number of vertices: ");
       int numVertices = scan.nextInt();

       DiGraph graph = new DiGraph(numVertices);

       //printing menu
       System.out.println();
       System.out.println("Choose one of the following operations:");
       System.out.println("-add edge (enter a)");
       System.out.println("-delete edge (enter d)");
       System.out.println("-edge count (enter e)");
       System.out.println("-vertex count (enter v)");
       System.out.println("-print graph (enter p)");
       System.out.println("-Quit (enter q)");

       //flushing newline character
       scan.nextLine();

       String option = scan.nextLine();

       while(!(option.equals("q"))) {

            if(option.equals("a") ||
                option.equals("d") ||
                option.equals("e") ||
                option.equals("v") ||
                option.equals("p") ||
                option.equals("q") ||
                option.equals("t"))  {
                
                switch(option) {

                    case "a":
                        int from = scan.nextInt();
                        int to = scan.nextInt();

                        //flushing newline char
                        scan.nextLine();

                        graph.addEdge(from, to);
                        System.out.println("(" + from + "," + to + ") edge is now added to the graph"); 
                        break;

                    case "d":
                        from = scan.nextInt();
                        to = scan.nextInt();

                        //flushing newline char
                        scan.nextLine();

                        graph.deleteEdge(from, to);
                        System.out.println("(" + from + "," + to + ") edge is now removed from the graph"); 
                        break;

                    case "v":
                        System.out.println("Number of vertices is " + graph.vertexCount());
                        break;

                    case "e":
                        System.out.println("Number of edges is " + graph.edgeCount());
                        break;

                    case "p":
                        System.out.println("The graph is the following:");
                        graph.print();
                        break;

                    case "t":
                        try {
                            
                            System.out.println("Topological Sort: "); 
                            int[] array = graph.topSort();
                            for(int i = 0; i < array.length; i++) {
                                if(i == array.length-1) {
                                    System.out.print(array[i]+1);
                                }
                                else {
                                    System.out.print(array[i]+1 + ",");
                                }
                            }
                            System.out.println();
                        }
                        catch (IllegalArgumentException e){
                            System.out.println("Graph is cyclic");
                        } 
                }
            }
            else {
                System.out.println("Invalid operation");

            }
            option = scan.nextLine();
        }
        System.out.println("Good bye.");
    }
}