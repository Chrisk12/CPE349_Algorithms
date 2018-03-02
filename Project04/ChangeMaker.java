/*
* @Author: cpesc
* @Date:   2018-03-01 16:40:35
* @Last Modified by:   Chris Kim
* @Last Modified time: 2018-03-01 18:31:09
*/

import java.lang.Integer;
import java.util.Scanner;
import java.util.Arrays;

public class ChangeMaker {

    public Scanner scan = new Scanner(System.in);

    public static int[] change_DP(int n, int[] d) {
        int[] changeArr = new int[n + 1];
        int[] auxArr = new int[n + 1];
        int[] result = new int[d.length];
        int auxLen = n;
        Integer minIndex = null;

        for (int j = 1; j < changeArr.length; j++ ) {

            for (int i = 0; i < d.length; i++) {
                
                int possibleMinIndex = j-d[i];

                if (possibleMinIndex >= 0) {

                    if (minIndex != null) {

                        if (changeArr[minIndex] > changeArr[possibleMinIndex]) {

                            minIndex = possibleMinIndex;
                            auxArr[j] = d[i];
                        }

                    }
                    else {
                        minIndex = possibleMinIndex;
                        auxArr[j] = d[i];
                    }
                    System.out.println("Index: " + j);
                    
                }
                
            }
            changeArr[j] = changeArr[minIndex] + 1;
            minIndex = null;
            System.out.println("CHANGE: " + changeArr[j]);

        }

        System.out.println("AUX :" + Arrays.toString(auxArr));
        System.out.println();
        System.out.println();
        System.out.println("CHANGE: " + Arrays.toString(changeArr));

        while (auxLen > 0) {

            System.out.println("AUXLEN: " + auxLen);


            for(int i = 0; i < d.length; i++) {
                // System.out.println("d[i]: " + d[i]);
                // System.out.println("arr: " + auxArr[auxLen]);
                if (d[i] == auxArr[auxLen]) {

                    result[i] += 1;
                
                }

            }
            auxLen -= auxArr[auxLen];
            
        }

        return result;

    }



    public static void main(String[] args) {
        
        int[] intD = {100, 25,10,5,1};
        // int[] intD = {25,12,5,1};
        int change = 233;

        int[] Yo = change_DP(change, intD);

        System.out.println(Arrays.toString(Yo));

    }

}