/*
* @Author: Chris Kim, Thinh Luu
* @Usernames: ckim65, tpluu
* @Date:   2018-03-01 16:40:35
* @Last Modified by:   tpluu
* @Last Modified time: 2018-03-02 14:49:11
*/

import java.lang.Integer;
import java.util.Scanner;
import java.util.Arrays;

public class ChangeMaker {


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
                    
                }
                
            }
            changeArr[j] = changeArr[minIndex] + 1;
            minIndex = null;

        }



        while (auxLen > 0) {


            for(int i = 0; i < d.length; i++) {

                if (d[i] == auxArr[auxLen]) {

                    result[i] += 1;
                
                }

            }
            auxLen -= auxArr[auxLen];
            
        }

        return result;

    }

    public static int[] change_greedy(int n, int[] d) {
        int[] result = new int[d.length];
        int auxLen = n;
        Integer minIndex = null;

        int i = 0;

        while (n > 0 && i < d.length) {

            if (n >= d[i]) {

                result[i] += 1;
                n -= d[i];

            }
            else {
                i++;
            }

        }

        return result;

    }

    public static void printCount(int[] toPrint){
        int count = 0;
        for (int i = 0; i < toPrint.length; i++) {
            
            count += toPrint[i];

        }
        System.out.println("Optimal coin count: " + count);
        System.out.println();
    }

    public static void printResults(int[] toPrint, int[] intD, int n){
        boolean firstPrint = true;
        System.out.println("Amount: " + n);
        System.out.print("Optimal distribution: ");
        for (int i = 0; i < toPrint.length; i++) {
            if (toPrint[i] != 0){

                if (firstPrint) {
                    System.out.print("" + toPrint[i] + "*" + intD[i] +"c");
                    firstPrint = false;
                }
                else { 

                    System.out.print(" + " + toPrint[i] + "*" + intD[i] +"c");

                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numDoms = -1;
        int n = -1;

        System.out.println("Enter the number of coin-denominations and the set of coin values:");
        numDoms = scan.nextInt();
        int [] intD = new int[numDoms];
        for (int i = 0; i < intD.length; i++) {
            intD[i] = scan.nextInt();
        }
        System.out.println();
        while (n != 0) {
            System.out.println("Enter a positive amount to be changed (enter 0 to quit):");
            n =  scan.nextInt();
            System.out.println();

            if (n == 0){
                System.out.println("Thanks for playing. Good Bye.");
                return;
            }

            int[] result = change_DP(n, intD);

            System.out.println("DP algorithm results");
            printResults(result,intD, n);
            printCount(result);

            result = change_greedy(n, intD);

            System.out.println("Greedy algorithm results");
            printResults(result,intD, n);
            printCount(result);
        }
    }

}