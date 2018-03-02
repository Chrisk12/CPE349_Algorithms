/*

* @Author: Chris Kim, Thinh Luu
* @Usernames: ckim65, tpluu
* @Last Modified by:   tpluu
* @Last Modified time: 2018-02-14 14:33:20

*/

import java.io.*;
import java.util.*;
import java.lang.Math.*;

public class FactoryProblem {

     public static void BottomUpFactory(int n, int[] lineOne, int [] lineTwo, int[] transOne, 
          int[] transTwo, int e1, int e2, int x1, int x2){

          int Res1[] = new int[n];
          int Res2[] = new int[n];

          Res1[0] = e1 + lineOne[0];
          Res2[0] = e2 + lineTwo[0];

          int[][] path = new int[2][n-1];

          int startPath = myMin(lineOne, lineTwo, transOne, transTwo, Res1, Res2, path, n, x1, x2);

          System.out.println("Fastest time is: " + Math.min(Res1[n-1] + x1, Res2[n-1] + x2));
          System.out.println();
          System.out.println("The optimal route is:");

          printPathRecurse(path,startPath,n);
     }

     private static  void printPathRecurse(int[][] path, int startPath, int n) {

            printPathRecurse(path, startPath, startPath, n-1);
     }

     private static void printPathRecurse(int[][] path, int startPath,int startPath2, int n) {

            if(n == 0) {
                System.out.println("station " + (n+1) + ", line " + (startPath2+1));
            }
            else {
                printPathRecurse(path,startPath,path[startPath2][n-1],n-1);
                System.out.println("station " + (n+1) + ", line " + (startPath2+1));
            }
     }

     private static int myMin(int[] lineOne, int [] lineTwo, int[] transOne, 
          int[] transTwo, int Res1[], int Res2[], int[][] path, int n, int x1, int x2) {

        for (int i = 1; i < n; i++){

            if(Res1[i-1] + lineOne[i] < Res2[i-1]+transTwo[i-1]+lineOne[i]) {
                Res1[i] = Res1[i-1] + lineOne[i];
                path[0][i-1] = 0;
            }
            else {
                Res1[i] = Res2[i-1]+transTwo[i-1]+lineOne[i];
                path[0][i-1] = 1;

            }

            if(Res2[i-1] + lineTwo[i] < Res1[i-1]+transOne[i-1]+lineTwo[i]) {
                Res2[i] = Res2[i-1] + lineTwo[i];
                path[1][i-1] = 1;
            }
            else {
                Res2[i] = Res1[i-1]+transOne[i-1]+lineTwo[i];
                path[1][i-1] = 0;

            }
        }

        if(Res1[n-1] + x1 < Res2[n-1] + x2) {
            return 0;
        }
        else{
            return 1;
        }
    }


     public static void main(String[] args) {

          
          Scanner read = new Scanner(System.in);
          System.out.print("Enter a file name: ");
          String filename = read.nextLine();
          filename = filename.trim();

          File file = new File(filename);

          try {
                read = new Scanner(file);
          }
          catch (FileNotFoundException e) {

          }

          int n = read.nextInt();
          int[] lineOne= new int[n];
          int[] lineTwo = new int[n];
          int[] transOne = new int[n-1];
          int[] transTwo = new int[n-1];
          int e1 = read.nextInt();
          int e2 = read.nextInt();
          int x1 = read.nextInt();
          int x2 = read.nextInt();
          for (int i = 0; i < n; i++){
                lineOne[i] = read.nextInt();
          }
          for (int i = 0; i < n; i++){
                lineTwo[i] = read.nextInt();
          }
          for (int i = 0; i < n-1; i++){
                transOne[i] = read.nextInt();
          }
          for (int i = 0; i < n-1; i++){
                transTwo[i] = read.nextInt();
          }

          BottomUpFactory(n, lineOne, lineTwo, transOne, transTwo, e1, e2, x1, x2);

     }
} 