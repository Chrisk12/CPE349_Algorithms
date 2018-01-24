/*
* @Author: Thinh Luu
* @Date:   2018-01-23 23:10:36
* @Last Modified by:   tpluu
* @Last Modified time: 2018-01-24 11:21:38
*/

import java.util.*;
import java.io.*;

public class MatrixWork{

   public static void main(String[] args) {

      int numRowA;
      int numColA;
      int numRowB;
      int numColB;

      int[][] A;
      int[][] B;
      int[][] product;
      
      String fileName;
      Scanner input = new Scanner(System.in);

      System.out.print("Enter file: ");

      File file = new File(input.next());
      input.close();

      try {
         Scanner fileScanner = new Scanner(file);

         //retrieving dimensions for matrix A
         numRowA = fileScanner.nextInt();
         numColA = fileScanner.nextInt();

         A = new int[numRowA][numColA];

         //filling in Matrix A
         for(int i = 0; i < numRowA; i++) {

            for(int j = 0; j < numColA; j++){
               A[i][j] = fileScanner.nextInt();
            }
         }

         //retrieving dimensions for matrix B
         numRowB = fileScanner.nextInt();
         numColB = fileScanner.nextInt();

         B = new int[numRowB][numColB]; 

         //filling in Matrix B
         for(int i = 0; i < numRowB; i++) {

            for(int j = 0; j < numColB; j++){
               B[i][j] = fileScanner.nextInt();
            }
         }

         //Computing Product
         try{

            product = matrixProduct(A,B);
            printMatrix(product, numRowA, numColB);
         }
         catch(IllegalArgumentException e) {
            System.out.println("Invalid Matrices");
            return;
         }
      }

      catch(FileNotFoundException e) {
         System.out.println("File Not Found.");
         return;
      }
   }

   public static int[][] matrixProduct(int[][] A, int[][] B) {

      int numColA = A[0].length;
      int numRowA = A.length;
      int numColB = B[0].length;
      int numRowB = B.length;

      if (numColA != numRowB) { 
         throw new IllegalArgumentException();
      }

      int[][] product = new int[numRowA][numColB];

      //going through the A rows
      for (int i = 0; i < numRowA; i++) {
         //going through B cols
         for (int j = 0; j < numColB; j++) {
            //going through A cols and B rows
            for (int k = 0; k < numColA; k++) {
               
               product[i][j] += A[i][k] * B[k][j];
            }
         }
      }

      return product;
   }

   private static void printMatrix(int[][] product, int numRowA, int numColB) {

      System.out.println("Product Matrix:");

      for (int i = 0; i < numRowA; i++) {
         for (int j = 0; j < numColB; j++) {
            
            System.out.print(product[i][j]+ " ");
            
         }
         System.out.println();
      }
   }
}