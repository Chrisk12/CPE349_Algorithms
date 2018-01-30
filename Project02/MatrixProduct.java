/*
* @Author: Thinh Luu, Chris Kim
* @Date:   2018-01-29 23:15:14
* @Last Modified by:   thinhluu
* @Last Modified time: 2018-01-30 02:23:34
*/

import java.util.*;

public class MatrixProduct {

   //Public divide and conquer Matrix Multiplication
   public static int[][] matrixProduct_DAC(int[][] A, int[][] B) {

      checkInputs(A,B);

      int[][] product = matrixProduct_DAC(A,0,0,B,0,0,A.length);

      return product;
   }

   //private recrusive method; first array: A, second array B
   private static int[][] matrixProduct_DAC(int[][] A, int startrowA, int startcolA, int[][] B, int startrowB, int startcolB, int n) {

      int[][] product = new int[n][n];

      //base case
      if(n == 1) {
         product[0][0] = A[startrowA][startcolA] * B[startrowB][startcolB];
      }
      else {
         //filling in quadrants
         int newSize = n/2;

         //product_11
         add_DAC(
            matrixProduct_DAC(A,startrowA,startcolA,B, startrowB,startcolB, newSize), 
            matrixProduct_DAC(A,startrowA,startcolA+newSize,B, startrowB+newSize,startcolB, newSize),
            product, 0, 0);

         //product_12
         add_DAC(
            matrixProduct_DAC(A,startrowA,startcolA,B, startrowB,startcolB+newSize, newSize), 
            matrixProduct_DAC(A,startrowA,startcolA+newSize,B, startrowB+newSize,startcolB+newSize, newSize),
            product, 0, newSize);


         //product_21
         add_DAC(
            matrixProduct_DAC(A,startrowA+newSize,startcolA,B, startrowB,startcolB, newSize), 
            matrixProduct_DAC(A,startrowA+newSize,startcolA+newSize,B, startrowB+newSize,startcolB, newSize),
            product, newSize, 0);

         //product_22
         add_DAC(
            matrixProduct_DAC(A,startrowA+newSize,startcolA,B, startrowB,startcolB+newSize, newSize), 
            matrixProduct_DAC(A,startrowA+newSize,startcolA+newSize,B, startrowB+newSize,startcolB+newSize, newSize),
            product, newSize, newSize);
      }

      return product;
   }
   //fills adds matrix A and B and fills Product matrix
   private static void add_DAC(int [][] A, int [][] B, int[][] Product, int startrowProduct, int startcolProduct) {

      int size = A.length;

      for(int i = 0; i < size; i++){
         for (int j = 0; j < size; j++) {
            Product[i+startrowProduct][j+startcolProduct] = A[i][j]+B[i][j];
         }
      }
   }

   //Strassen's divide and conquer Matrix Multiplication
   public static int[][] matrixProduct_Strassen(int[][] A, int[][] B) {

      checkInputs(A,B);
   }

   //private recrusive method; first array: A, second array B
   private static int[][] matrixProduct_Strassen(int[][] A, int startrowA, int startcolA, int[][] B, int startrowB, int startcolB, int n) {

      int[][] product = new int[n][n];

      //base case
      if(n == 1) {
         product[0][0] = A[startrowA][startcolA] * B[startrowB][startcolB];
      }
      else {
         int newSize = n/2;


      }
   } 

   private void add_Strassen()



   private static void checkInputs(int[][] A, int[][] B) {

      int numColA = A[0].length;
      int numRowA = A.length;
      int numColB = B[0].length;
      int numRowB = B.length;

      //throw exception if not square matrices of same size and size not power of 2
      if(numRowA != numColA || numRowB != numColB || numRowA != numRowB || numRowA%2 != 0) {
         throw new IllegalArgumentException();
      }
   }
}