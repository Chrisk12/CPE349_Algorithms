/*
* @Author: chriskim
* @Date:   2018-01-10 09:16:14
* @Last Modified by:   chriskim
* @Last Modified time: 2018-01-11 08:24:42
*/
import java.util.Random;
import java.util.Arrays;

public class Sorts {

   public static void selectionSort(int[] arr, int N) {

      for (int i = 0; i < N; i++) {

         int temp = i;
         for (int j = i; j < N; j++) {

            if (arr[temp] > arr[j]) {

               temp = j;

            }

         }
         swap(arr,i,temp);

      }

   }

   public static void mergeSort(int [] arr, int N) {
      
       

   }

   public static void quickSort(int [] arr, int N) {
      
   }

   private static void swap(int[] arr, int i, int j) {

      int temp = arr[i];
      arr[i] = arr[j];
      arr[j] = temp;

   }

   public static void main(String[] args) {
      
      int[] arr = new int[10];

      Random rand = new Random();
      for (int i = 0; i < 10;  i++) {
      
         arr[i] = rand.nextInt(50) + 1;

      }

      System.out.println("ORIGINAL ARRAY: " + Arrays.toString(arr));

      selectionSort(arr, arr.length);

      System.out.println("NEW ARRAY: " + Arrays.toString(arr));


   }

}