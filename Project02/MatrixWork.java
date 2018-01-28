/*
* @Author: chriskim
* @Date:   2018-01-22 09:35:55
* @Last Modified by:   Chris Kim
* @Last Modified time: 2018-01-24 09:57:22
*/

import java.util.*;
import java.io.*;

public class MatrixWork {


    public static int[][] matrixProduct(int[][] firstArr, int[][] secondArr) {


        int firstRows = firstArr.length;
        int firstCols = firstArr[0].length;
        int secondRows = secondArr.length;
        int secondCols = secondArr[0].length;

        if (firstCols != secondRows) {
            throw new IllegalArgumentException();
        }

        int[][] product = new int[firstRows][secondCols];

        for(int i = 0; i < firstRows; i++) {

            for (int j = 0; j < secondCols ; j++) {

                for (int k = 0; k < firstCols ; k++ ) {

                    product[i][j] += (firstArr[i][k] * secondArr[k][j]);


                }
                
            }

        }

        return product;

    }

    public static void main(String[] args) {
        
        int[][] newArr1 = null;
        int[][] newArr2 = null;
        Scanner read = new Scanner(System.in);
        System.out.print("Enter a file name: ");
        String filename = read.nextLine();
        filename = filename.trim();

        File file = new File(filename);

        try {
            read = new Scanner(file);
        }
        catch (FileNotFoundException e) {

        }
        int i = 0;
        int j = 0;
        boolean firstArr = true;
        while (read.hasNextLine()) {

            String currLine = read.nextLine();

            int count = 0;
            String[] part = currLine.split(" ");
            
            i = Integer.parseInt(part[0]);
            j = Integer.parseInt(part[1]);

            

            if (firstArr) {
                newArr1 = new int[i][j];
            }
            else {

                newArr2 = new int[i][j];
            }

            currLine = read.nextLine();
            part = currLine.split(" ");
            for (int k = 0; k < j; k++) {
                if (firstArr) {

                    newArr1[count][k] = Integer.parseInt(part[k]);

                }
                else {
                    newArr2[count][k] = Integer.parseInt(part[k]);

                }
            }
            count++;
            while (count < i) {
                currLine = read.nextLine().trim();
                if (currLine.isEmpty()) {
                    continue;
                }
                part = currLine.split(" ");
                for (int k = 0; k < j; k++) {
                    if (firstArr) {

                        newArr1[count][k] = Integer.parseInt(part[k]);

                    }
                    else {
                        newArr2[count][k] = Integer.parseInt(part[k]);
                    }
                }
                count++;

            }
            if (firstArr) {
                firstArr = false;
            }

        }


        // int[][] old1 = {{1,2,3},{4,5,6}};
        // int[][] old2 = {{7,8},{9,10},{11,12}};


        // int[][] lol = matrixProduct(old1, old2);
        // System.out.println(Arrays.toString(lol[0]));
        // System.out.println(Arrays.toString(lol[1]));
        int[][] lol2 = null;
        try {
            lol2 = matrixProduct(newArr1, newArr2);
        }
        catch (IllegalArgumentException e){

            System.out.println("Invalid Matrices");
            return;

        }
        
        for (int z = 0; z < lol2.length; z++) {
            System.out.println(Arrays.toString(lol2[z]));

        }

    }

}