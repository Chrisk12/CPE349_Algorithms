/*
* @Author: Chris Kim, Thinh Luu
* @Usernames: ckim65, tpluu
* @Date:   2018-01-17 01:44:58
* @Last Modified by:   Chris Kim
* @Last Modified time: 2018-01-19 10:19:11
*/

import java.util.*;

public class SortCounts {
    
    public static void main(String[] args) {
        
        long startTime;
        long endTime;

        long time1;
        long time2;
        long time3;

        Random ran = new Random();
        Sorts1 sort = new Sorts1();

        // generates different sizes for each array
        for(int size = 100; size <= 12800; size*=2) {

        	long avgSS = 0;
        	long avgMS = 0;
        	long avgQS = 0;

            // repeating process 100 times for each array size
            for(int i = 0; i < 100; i++) {

                // initializing and copying 3 arrays for each sort
                int[] array1 = new int[12800];
                
                for(int j = 0; j < array1.length; j++) {

                    array1[j] = ran.nextInt(500);
                }

                int[] array2 = new int[12800];
                int[] array3 = new int[12800];

                System.arraycopy(array1, 0 , array2, 0, array1.length);
                System.arraycopy(array1, 0 , array3, 0, array1.length);

                // computing for selection sort
                avgSS += sort.selectionSort(array1, size);

                // computing for merge sort
                avgMS += sort.mergeSort(array2, size);

                // computing for quicks sort
                avgQS += sort.quickSort(array3, size);



                
            }
            displayResults(size, avgSS/100, avgMS/100, avgQS/100);
        }
    }

    // displays the number of elements and the times for each sort
    private static void displayResults(int N, long avgSS, long avgMS, long avgQS) {

        System.out.println("N="+N+": C_ss="+avgSS+", C_ms="+avgMS+", C_qs="+avgQS);
    }
}
