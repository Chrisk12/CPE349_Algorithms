//
//
//
import java.util.*;

public class SortTimes {
   
   public static void main(String[] args) {
      
      long startTime;
      long endTime;

      long time1;
      long time2;
      long time3;

      Random ran = new Random();
      Sorts sort = new Sorts();

      startTime = System.nanoTime() / 1000000;

      // generates different sizes for each array
      for(int size = 5000; size <= 16000; size*=2) {

         // repeating prpcess 5 times for each array size
         for(int i = 0; i < 5; i++) {

            // initializing and copying 3 arrays for each sort
            int[] array1 = new int[160000];
            
            for(int j = 0; j < array1.length; j++) {

               array1[j] = ran.nextInt(500);
            }

            int[] array2 = array1;
            int[] array3 = array1;

            // computing time for selection sort
            sort.selectionSort(array1, size);
            endTime = System.nanoTime() / 1000000;
            time1 = endTime - startTime;

            // computing time for merge sort
            sort.mergeSort(array2, size);
            endTime = System.nanoTime() / 1000000;
            time2 = endTime - startTime;

            // computing time for quicks sort
            sort.quickSort(array3, size);
            endTime = System.nanoTime() / 1000000;
            time3 = endTime - startTime;

            displayResults(size, time1, time2, time3);
         }
      }
   }

   // displays the number of elements and the times for each sort
   private static void displayResults(int N, long time1, long time2, long time3) {

      System.out.println("N="+N+": T_ss="+time1+", T_ms="+time2+"), T_qs="+time3);
   }
}
