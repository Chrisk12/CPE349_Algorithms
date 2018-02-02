/*
* @Author: Thinh Luu, Chris Kim
* @Date:   2018-01-29 23:15:14
* @Last Modified by:   tpluu
* @Last Modified time: 2018-02-01 23:25:41
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

         int[][] S1, S2, S3, S4, S5, S6, S7, S8, S9, S10, P1, P2, P3, P4, P5, P6, P7, C11, C12, C21, C22;

         S1 = sub_Strassen(B, startrowB, startcolB+newSize, B, startrowB+newSize, startcolB+newSize, newSize);
         S2 = add_Strassen(A, startrowA, startcolA, A, startrowA, startcolA+newSize, newSize);
         S3 = add_Strassen(A, startrowA+newSize, startcolA, A, startrowA+newSize, startcolA+newSize, newSize);
         S4 = sub_Strassen(B, startrowB+newSize, startcolB, B, startrowB, startcolB, newSize);
         S5 = add_Strassen(A, startrowA, startcolA, A, startrowA+newSize, startcolA+newSize, newSize);
         S6 = add_Strassen(B, startrowB, startcolB, B, startrowB+newSize, startcolB+newSize, newSize);
         S7 = sub_Strassen(A, startrowA, startcolA+newSize, A, startrowA+newSize, startcolA+newSize, newSize);
         S8 = add_Strassen(B, startrowB+newSize, startcolB, B, startrowB+newSize, startcolB+newSize, newSize);
         S9 = sub_Strassen(A, startrowA, startcolA, A, startrowA+newSize, startcolA, newSize);
         S10 = add_Strassen(B, startrowB, startcolB, B, startrowB, startcolB+newSize, newSize);

         P1 = matrixProduct_Strassen(A, startrowA, startcolA, S1, 0, 0, newSize);
         P2 = matrixProduct_Strassen(S2, 0, 0, B, startrowB+newSize, startcolB+newSize, newSize);
         P3 = matrixProduct_Strassen(S3, 0, 0, B, startrowB, startcolB, newSize);
         P4 = matrixProduct_Strassen(A, startrowA+newSize, startcolA+newSize, S4, 0, 0, newSize);
         P5 = matrixProduct_Strassen(S5, 0, 0, S6, 0, 0, newSize);
         P6 = matrixProduct_Strassen(S7, 0, 0, S8, 0, 0, newSize);
         P7 = matrixProduct_Strassen(S9, 0, 0, S10, 0, 0, newSize);

         //P5 + P4
         int[][] temp = add_Strassen(P5, 0, 0, P4, 0, 0, newSize);
         int[][] temp2 = sub_Strassen(temp, 0, 0, P2, 0, 0, newSize);
         C11 = add_Strassen(temp2, 0, 0, P6, 0 ,0, newSize);

         int[][] temp3 = add_Strassen(P1, 0, 0, P5, 0, 0, newSize);
         int[][] temp4 = sub_Strassen(temp3, 0, 0, P3, 0, 0, newSize);
         C22 = sub_Strassen(temp4, 0, 0, P7, 0, 0, newSize);

         C12 = add_Strassen(P1, 0, 0, P2, 0, 0, newSize);
         C21 = add_Strassen(P3, 0, 0, P4, 0, 0, newSize);

         fill_Strassen(product, C11, 0, 0, newSize);
         fill_Strassen(product, C12, 0, newSize, newSize);
         fill_Strassen(product, C21, newSize, 0, newSize);
         fill_Strassen(product, C22, newSize, newSize, newSize);



         //C11 =  P5 + P4 - P2 +P6
         // fill_Strassen (

         //    product,
         //    add_Strassen(
         //       sub_Strassen(
         //          add_Strassen(

         //             //P5 = (S5 = A11 + A22) * (S6 = B11 + B22)
         //             matrixProduct_Strassen(

         //                //S5 = A11 + A22
         //                add_Strassen(
         //                   A, startrowA, startcolA, 
         //                   A, startrowA + newSize, startcolA  + newSize, 
         //                   newSize
         //                ), 0, 0,
         //                //s6 = B11 + B22
         //                add_Strassen(
         //                   B, startrowB, startcolB, 
         //                   B, startrowB + newSize, startcolB  + newSize, 
         //                   newSize
         //                ), 0, 0,

         //                newSize

         //             ), startrowA + newSize, startcolA + newSize,
         //             //P4 = A22 * (S4 = B21 - B11)
         //             matrixProduct_Strassen( 

         //                A, startrowA + newSize, startcolA + newSize,
         //                //S4 = B21 - B11
         //                sub_Strassen(
         //                   B, startrowB + newSize, startcolB, 
         //                   B, startrowB, startcolB, 
         //                   newSize), 0, 0,
         //                   newSize
         //                ), startrowA + newSize, startcolA + newSize,
         //             newSize
         //       ), startrowA + newSize, startrowA + newSize,
               
         //       //P2 = (S2 = A11 + A12) * B22              
         //       matrixProduct_Strassen(
         //          add_Strassen(
         //             A, startrowA, startcolA, 
         //             A, startrowA, startcolA+newSize, 
         //             newSize
         //          ), 0, 0, 
         //          B, startrowB +newSize, startcolB +newSize, 
         //          newSize
         //       ), startrowB +newSize, startcolB +newSize, 
         //       newSize
         //    ), 0, 0,
         //    // P6 = (S7 = A12 - A22) * (S8 = B21 + B22)
         //    matrixProduct_Strassen(

         //       //S7 = A12 - A22
         //       sub_Strassen(
         //          A, startrowA, startcolA + newSize,
         //          A, startrowA + newSize, startcolA  + newSize, 
         //          newSize
         //       ), 0, 0,
         //       //S8 = B21 + B22
         //       add_Strassen(

         //          B, startrowB + newSize, startcolB,
         //          B, startrowB +newSize, startcolB +newSize,
         //          newSize

         //       ), 0, 0,
         //       newSize

         //    ), 0, 0,
         //    newSize
         // ), 0, 0, newSize);

         // //C12 = P1 + P2
         // fill_Strassen(

         //    product,
         //    add_Strassen(
         //       // P1 = A11 * (S1=B12-B22)
         //       matrixProduct_Strassen(
         //          A, startrowA, startcolA, 
         //          sub_Strassen(
         //             B, startrowB, startcolB + newSize, 
         //             B, startrowB + newSize, startcolB + newSize, 
         //             newSize
         //          ), 0, 0, newSize
         //       ), startrowA, startcolA,
         //       //P2 = (S2 = A11 + A12) * B22
         //       matrixProduct_Strassen(
         //          add_Strassen(
         //             A, startrowA, startcolA, 
         //             A, startrowA, startcolA+newSize, 
         //             newSize
         //          ), 0, 0, 
         //          B, startrowB +newSize, startcolB +newSize, 
         //          newSize
         //       ), startrowB +newSize, startcolB +newSize, 
         //       newSize
         //    ), 0, newSize, //startrowA, startcolB + newSize, newSize
         //    newSize
         // );


         // //CC 22 = P5 + P1 - P3 - P7
         // fill_Strassen(

         //    product,

         //    sub_Strassen(
               
         //       sub_Strassen(
                  
         //          add_Strassen(

         //             //P5 = (S5 = A11 + A22) * (S6 = B11 + B22)
         //             matrixProduct_Strassen(

         //                //S5 = A11 + A22
         //                add_Strassen(
         //                   A, startrowA, startcolA, 
         //                   A, startrowA + newSize, startcolA  + newSize, 
         //                   newSize
         //                ), 0, 0,
         //                //s6 = B11 + B22
         //                add_Strassen(
         //                   B, startrowB, startcolB, 
         //                   B, startrowB + newSize, startcolB  + newSize, 
         //                   newSize
         //                ), 0, 0,

         //                newSize

         //             ), startrowA + newSize, startcolA + newSize,
         //             // P1 = A11 * (S1=B12-B22)
         //             matrixProduct_Strassen(
         //                A, startrowA, startcolA, 
         //                sub_Strassen(
         //                   B, startrowB, startcolB + newSize, 
         //                   B, startrowB + newSize, startcolB + newSize, 
         //                   newSize
         //                ), 0, 0, newSize
         //             ), startrowA, startcolA,
         //             newSize

         //          ), 0, 0,

         //          //P3 = (S3 = A21 + A22) * B11
         //          matrixProduct_Strassen(

         //             //S3 = A21 + A22
         //             add_Strassen(

         //                A, startrowA + newSize, startcolA,
         //                A, startrowA + newSize, startcolA  + newSize,
         //                newSize

         //             ), 0, 0,
         //             //B11
         //             B, startrowB, startcolB,
         //             newSize

         //          ), startrowB, startcolB,
         //          newSize
         //       ), 0, 0,

         //       //P7 = (S9 = A11 - A21) * (S10 = B11 + B12)
         //       matrixProduct_Strassen(

         //          //S9 = A11 - A21
         //          sub_Strassen(

         //             A, startrowA, startcolA,
         //             A, startrowA + newSize, startcolA,
         //             newSize

         //          ), 0 , 0,
         //          //S10 = B11 + B12 
         //          add_Strassen(

         //             B, startrowB, startcolB,
         //             B, startrowB, startcolB + newSize,
         //             newSize

         //          ), 0, 0,
         //          newSize

         //       ), 0, 0,
         //       newSize
            
         //    ), newSize, newSize,
         //    newSize
         // );

         // //C21
         // fill_Strassen(

         //    product,
         //    add_Strassen(

         //       //P3 = (S3 = A21 + A22) * B11
         //       matrixProduct_Strassen(

         //          //S3 = A21 + A22
         //          add_Strassen(

         //             A, startrowA + newSize, startcolA,
         //             A, startrowA + newSize, startcolA  + newSize,
         //             newSize

         //          ), 0, 0,
         //          //B11
         //          B, startrowB, startcolB,
         //          newSize

         //       ), startrowB, startcolB,
         //       //P4 = A22 * (S4 = B21 - B11)
         //       matrixProduct_Strassen( 

         //          A, startrowA + newSize, startcolA + newSize,
         //          //S4 = B21 - B11
         //          sub_Strassen(
         //             B, startrowB + newSize, startcolB, 
         //             B, startrowB, startcolB, 
         //             newSize), 0, 0,
         //             newSize
         //          ), startrowA + newSize, startcolA + newSize,
         //       newSize
         //    ),
         //    newSize, 0,
         //    newSize
         // );

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
            product[startRow + i][startCol + j] = toFill[i][j];
         }
      }
   }

   private static void checkInputs(int[][] A, int[][] B) {

      int numColA = A[0].length;
      int numRowA = A.length;
      int numColB = B[0].length;
      int numRowB = B.length;

      double logSize = Math.log(numRowA) / Math.log(2);
      double logSizeFloor = Math.floor(logSize);

      //throw exception if not square matrices of same size and size not power of 2
      if(numRowA != numColA || numRowB != numColB || numRowA != numRowB || logSize != logSizeFloor) {
         throw new IllegalArgumentException();
      }
   }
}