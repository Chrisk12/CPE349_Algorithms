/*
* @Author: Chris Kim, Thinh Luu
* @Usernames: ckim65, tpluu
* @Date:   2018-01-10 09:16:14
* @Last Modified by:   tpluu
* @Last Modified time: 2018-01-19 14:46:05
*/

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
        for(int size = 5000; size <= 160000; size*=2) {

            // repeating prpcess 5 times for each array size
            for(int i = 0; i < 5; i++) {

                // initializing and copying 3 arrays for each sort
                int[] array1 = new int[160000];
                
                for(int j = 0; j < array1.length; j++) {

                    array1[j] = ran.nextInt(size);
                }

                int[] array2 = new int[160000];
                int[] array3 = new int[160000];

                System.arraycopy(array1, 0 , array2, 0, array1.length);
                System.arraycopy(array1, 0 , array3, 0, array1.length);

                // computing time for selection sort
                startTime = System.nanoTime() / 1000000;
                sort.selectionSort(array1, size);
                endTime = System.nanoTime() / 1000000;
                time1 = endTime - startTime;

                // computing time for merge sort
                startTime = System.nanoTime() / 1000000;
                sort.mergeSort(array2, size);
                endTime = System.nanoTime() / 1000000;
                time2 = endTime - startTime;

                // computing time for quicks sort
                startTime = System.nanoTime() / 1000000;
                sort.quickSort(array3, size);
                endTime = System.nanoTime() / 1000000;
                time3 = endTime - startTime;

                displayResults(size, time1, time2, time3);
            }
            System.out.println();
        }
    }

    // displays the number of elements and the times for each sort
    private static void displayResults(int N, long time1, long time2, long time3) {

        System.out.println("N="+N+": T_ss="+time1+", T_ms="+time2+", T_qs="+time3);
    }
}
