/*
* @Author: thinhluu
* @Date:   2018-01-30 01:40:47
* @Last Modified by:   thinhluu
* @Last Modified time: 2018-01-30 01:46:22
*/

import java.io.*;
import java.util.*;

public class TestDriver{
   
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
            MatrixProduct mp = new MatrixProduct();
            product = mp.matrixProduct_DAC(A,B);
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