/*
* @Author: Chris Kim, Thinh Luu
* @Usernames: ckim65, tpluu
* @Last Modified by:   tpluu
* @Last Modified time: 2018-02-14 14:33:45
*/

import java.io.*;
import java.util.*;
import java.lang.Math.*;

public class GameProblem {

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
      int m = read.nextInt();

      int[][] A = new int[n][m];

      for(int i = 0; i < n; i++) {
         for(int j = 0; j < m; j++) {
            A[i][j] = read.nextInt();
         }
      }

      game(n, m, A);
   }
   public static void game(int n, int m, int[][] A) {
      
      int[][] S = new int[n][m];
      char[][] R =  new char[n][m];
      
      myMax(n, m, A, S, R);
   }

   // personal max method that fills S and R, position of max(row,col), and max
   private static void myMax(int n, int m, int[][] A, int[][] S, char[][] R) {

      int max = -1;
      int row = -1;
      int col = -1;

      // starting calculation from bottom right corner of table
      for(int i = n-1; i >= 0; i--) {
         for(int j = m-1; j >= 0; j--) {

            // bottom corner
            if(i == n-1 && j == m-1) {
               S[i][j] = A[i][j];
               R[i][j] = 'e';
            }
            // last col
            else if(j == m-1){
               S[i][j] = Math.max(S[i+1][j], 0) + A[i][j];

               // going down or exiting
               if(S[i+1][j] > 0) {
                  R[i][j] = 'd';
               }
               else {
                  R[i][j] = 'e';
               }
            }
            // last row
            else if(i == n-1){
               S[i][j] = Math.max(S[i][j+1], 0) + A[i][j];

               // going right or exiting
               if(S[i][j+1] > 0) {
                  R[i][j] = 'r';
               }
               else {
                  R[i][j] = 'e';
               }
            }
            // in between
            else {
               S[i][j] = Math.max(S[i+1][j], S[i][j+1]) + A[i][j]; 

               // going down or right
               if(S[i+1][j] > S[i][j+1]) {
                  R[i][j] = 'd';
               }
               else {
                  R[i][j] = 'r';
               }
            }

            // updating max and max position
            if(S[i][j] > max) {
               max = S[i][j];
               row = i;
               col = j;
            }
         }
      }

      System.out.println("Best score: " + max);
      System.out.print("Best route: ");
      printPath(R, row, col);
      System.out.println();
   }

   private static void printPath(char[][] R,int row, int col) {

      while(R[row][col] != 'e') {

         //print yourself
         System.out.print("["+(row+1) + "," + (col+1) +"] to ");

         //if down, increment x, going down
         if(R[row][col] == 'd') {
            row += 1;
         }

         //if right, increment y, going right
         else if(R[row][col] == 'r') {
            col += 1;
         }
      }

      //at last position before exiting
      System.out.print("["+(row+1) + "," + (col+1) +"] to exit");
   }
}