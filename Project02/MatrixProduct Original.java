/*
* @Author: Thinh Luu, Chris Kim
* @Date:   2018-01-29 23:15:14
* @Last Modified by:   Chris Kim
* @Last Modified time: 2018-01-30 17:58:04
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
      return matrixProduct_Strassen(A, 0, 0, B, 0, 0, A.length);
   }

   // private recrusive method; first array: A, second array B
   private static int[][] matrixProduct_Strassen(int[][] A, int startrowA, int startcolA, int[][] B, int startrowB, int startcolB, int n) {

      int[][] product = new int[n][n];

      //base case
      if(n == 1) {
         
         product[0][0] = A[startrowA][startcolA] * B[startrowB][startcolB];
      
      }
      else {
         int newSize = n/2;

         //C11 =  P5 + P4 - P2 +P6
         fill_Strassen (

            product,
            add_Strassen(
               sub_Strassen(
                  add_Strassen(

                     //P5 = (S5 = A11 + A22) * (S6 = B11 + B22)
                     matrixProduct_Strassen(

                        //S5 = A11 + A22
                        add_Strassen(
                           A, startrowA, startcolA, 
                           A, startrowA + newSize, startcolA  + newSize, 
                           newSize
                        ), 0, 0,
                        //s6 = B11 + B22
                        add_Strassen(
                           B, startrowB, startcolB, 
                           B, startrowB + newSize, startcolB  + newSize, 
                           newSize
                        ), 0, 0,

                        newSize

                     ), startrowA + newSize, startcolA + newSize,
                     //P4 = A22 * (S4 = B21 - B11)
                     matrixProduct_Strassen( 

                        A, startrowA + newSize, startcolA + newSize,
                        //S4 = B21 - B11
                        sub_Strassen(
                           B, startrowB + newSize, startcolB, 
                           B, startrowB, startcolB, 
                           newSize), 0, 0,
                           newSize
                        ), startrowA + newSize, startcolA + newSize,
                     newSize
               ), startrowA + newSize, startrowA + newSize,
               
               //P2 = (S2 = A11 + A12) * B22              
               matrixProduct_Strassen(
                  add_Strassen(
                     A, startrowA, startcolA, 
                     A, startrowA, startcolA+newSize, 
                     newSize
                  ), 0, 0, 
                  B, startrowB +newSize, startcolB +newSize, 
                  newSize
               ), startrowB +newSize, startcolB +newSize, 
               newSize
            ), 0, 0,
            // P6 = (S7 = A12 - A22) * (S8 = B21 + B22)
            matrixProduct_Strassen(

               //S7 = A12 - A22
               sub_Strassen(
                  A, startrowA, startcolA + newSize,
                  A, startrowA + newSize, startcolA  + newSize, 
                  newSize
               ), 0, 0,
               //S8 = B21 + B22
               add_Strassen(

                  B, startrowB + newSize, startcolB,
                  B, startrowB +newSize, startcolB +newSize,
                  newSize

               ), 0, 0,
               newSize

            ), 0, 0,
            newSize
         ), 0, 0, newSize);

         //C12 = P1 + P2
         fill_Strassen(

            product,
            add_Strassen(
               // P1 = A11 * (S1=B12-B22)
               matrixProduct_Strassen(
                  A, startrowA, startcolA, 
                  sub_Strassen(
                     B, startrowB, startcolB + newSize, 
                     B, startrowB + newSize, startcolB + newSize, 
                     newSize
                  ), 0, 0, newSize
               ), startrowA, startcolA,
               //P2 = (S2 = A11 + A12) * B22
               matrixProduct_Strassen(
                  add_Strassen(
                     A, startrowA, startcolA, 
                     A, startrowA, startcolA+newSize, 
                     newSize
                  ), 0, 0, 
                  B, startrowB +newSize, startcolB +newSize, 
                  newSize
               ), startrowB +newSize, startcolB +newSize, 
               newSize
            ), 0, newSize, //startrowA, startcolB + newSize, newSize
            newSize
         );


         //CC 22 = P5 + P1 - P3 - P7
         fill_Strassen(

            product,

            sub_Strassen(
               
               sub_Strassen(
                  
                  add_Strassen(

                     //P5 = (S5 = A11 + A22) * (S6 = B11 + B22)
                     matrixProduct_Strassen(

                        //S5 = A11 + A22
                        add_Strassen(
                           A, startrowA, startcolA, 
                           A, startrowA + newSize, startcolA  + newSize, 
                           newSize
                        ), 0, 0,
                        //s6 = B11 + B22
                        add_Strassen(
                           B, startrowB, startcolB, 
                           B, startrowB + newSize, startcolB  + newSize, 
                           newSize
                        ), 0, 0,

                        newSize

                     ), startrowA + newSize, startcolA + newSize,
                     // P1 = A11 * (S1=B12-B22)
                     matrixProduct_Strassen(
                        A, startrowA, startcolA, 
                        sub_Strassen(
                           B, startrowB, startcolB + newSize, 
                           B, startrowB + newSize, startcolB + newSize, 
                           newSize
                        ), 0, 0, newSize
                     ), startrowA, startcolA,
                     newSize

                  ), 0, 0,

                  //P3 = (S3 = A21 + A22) * B11
                  matrixProduct_Strassen(

                     //S3 = A21 + A22
                     add_Strassen(

                        A, startrowA + newSize, startcolA,
                        A, startrowA + newSize, startcolA  + newSize,
                        newSize

                     ), 0, 0,
                     //B11
                     B, startrowB, startcolB,
                     newSize

                  ), startrowB, startcolB,
                  newSize
               ), 0, 0,

               //P7 = (S9 = A11 - A21) * (S10 = B11 + B12)
               matrixProduct_Strassen(

                  //S9 = A11 - A21
                  sub_Strassen(

                     A, startrowA, startcolA,
                     A, startrowA + newSize, startcolA,
                     newSize

                  ), 0 , 0,
                  //S10 = B11 + B12 
                  add_Strassen(

                     B, startrowB, startcolB,
                     B, startrowB, startcolB + newSize,
                     newSize

                  ), 0, 0,
                  newSize

               ), 0, 0,
               newSize
            
            ), newSize, newSize,
            newSize
         );

         //C21
         fill_Strassen(

            product,
            add_Strassen(

               //P3 = (S3 = A21 + A22) * B11
               matrixProduct_Strassen(

                  //S3 = A21 + A22
                  add_Strassen(

                     A, startrowA + newSize, startcolA,
                     A, startrowA + newSize, startcolA  + newSize,
                     newSize

                  ), 0, 0,
                  //B11
                  B, startrowB, startcolB,
                  newSize

               ), startrowB, startcolB,
               //P4 = A22 * (S4 = B21 - B11)
               matrixProduct_Strassen( 

                  A, startrowA + newSize, startcolA + newSize,
                  //S4 = B21 - B11
                  sub_Strassen(
                     B, startrowB + newSize, startcolB, 
                     B, startrowB, startcolB, 
                     newSize), 0, 0,
                     newSize
                  ), startrowA + newSize, startcolA + newSize,
               newSize
            ),
            newSize, 0,
            newSize
         );

      }
      return product;
   } 

   private static int[][] add_Strassen(int[][] A, int startrowA, int startcolA, int[][] B, int startrowB, int startcolB, int n) {

      int[][] product = new int[n][n];

      for(int i = 0; i < n; i++){
         for (int j = 0; j < n; j++) {
            product[i][j] = A[i+startrowA][j+startcolA]+B[i+startrowB][j+startcolB];
         }
      }

      return product;
   }

   private static int[][] sub_Strassen(int[][] A, int startrowA, int startcolA, int[][] B, int startrowB, int startcolB, int n) {

      int[][] product = new int[n][n];

      for(int i = 0; i < n; i++){
         for (int j = 0; j < n; j++) {
            product[i][j] = A[i+startrowA][j+startcolA]-B[i+startrowB][j+startcolB];
         }
      }

      return product;

   }

   private static void fill_Strassen(int[][] product, int[][] toFill, int startRow, int startCol, int n) {

      for(int i = 0; i < n; i++){
         for (int j = 0; j < n; j++) {
            product[startRow][startCol] = toFill[i][j];
         }
      }

   }

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