/*
* @Author: cpesc
* @Date:   2018-02-13 17:13:48
* @Last Modified by:   cpesc
* @Last Modified time: 2018-02-13 20:50:32
*/
import java.io.*;
import java.util.*;

public class FactoryProblem2 {

    public static void BottomUpFactory(int n, int[] lineOne, int [] lineTwo, int[] transOne, 
        int[] transTwo, int e1, int e2, int x1, int x2){

        System.out.println(n);
        System.out.println("" + e1 + ' ' + e2);
        System.out.println("" + x1 + ' ' + x2);
        System.out.println(Arrays.toString(lineOne));
        System.out.println(Arrays.toString(lineTwo));
        System.out.println(Arrays.toString(transOne));
        System.out.println(Arrays.toString(transTwo));

        int[] position = new int[n];
        int sum = 0;
        int currentLine = -1;

        for(int i = 0; i < n; i++){

            //for length of 1 add the optimal
            if (n == 1){
                if ((e1 + lineOne[0] + x1) < (e2 + lineTwo[0] + x2)){
                    sum = (e1 + lineOne[0] + x1);
                    position[0] = 0;
                }
                else {
                    sum = (e2 + lineTwo[0] + x2);
                   position[0] = 1;
                }
            }
            //base case if n != 1
            else if (i == 0) {
                if ((e1 + lineOne[0]) < (e2 + lineTwo[0])){
                    sum = (e1 + lineOne[0]);
                    position[0] = 0;
                }
                else {
                    sum = (e2 + lineTwo[0]);
                    position[0] = 1;
                }
            }
            else if (i == n-1){

                //if on line 2 find best path for exiting
                if (position[i-1] == 1){
                    if ((transTwo[i-1] + lineOne[i] + x1) < (lineTwo[i] + x2)){
                        sum += (transTwo[i-1] + lineOne[i] + x1);
                        position[i] = 0;
                    }
                    else {
                        sum += lineTwo[i] + x2;
                        position[i] = position[i-1];
                    }
                }
                //if on line 1 find best path for exiting
                else {

                    if ((transOne[i-1] + lineTwo[i] + x2) < (lineOne[i] + x1)){
                        sum += (transOne[i-1] + lineTwo[i] + x2);
                        position[i] = 1;
                    }
                    else {
                        sum += lineOne[i] + x1;
                        position[i] = position[i-1];
                    }

                }

            }
            else {
                //if on line 2 find best path
                if (position[i-1] == 1){
                    if ((transTwo[i-1] + lineOne[i]) < lineTwo[i]){
                        sum += (transTwo[i-1] + lineOne[i]);
                        position[i] = 0;
                    }
                    else {
                        sum += lineTwo[i];
                        position[i] = position[i-1];
                    }
                }
                //if on line 1 find best path
                else {

                    if ((transOne[i-1] + lineTwo[i]) < lineOne[i]){
                        sum += (transOne[i-1] + lineTwo[i]);
                        position[i] = 1;
                    }
                    else {
                        sum += lineOne[i];
                        position[i] = position[i-1];
                    }

                }

            }

        }

        System.out.println("Fastest time is: " + sum);
        System.out.println("The optimal route is:");

        for (int i = 0; i < n; i++) {
            System.out.println("station " + (i+1) + ", line " + (position[i] + 1));
        }

    }



    public static void main(String[] args) {

        
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

        int n = read.nextInt();
        int[] lineOne= new int[n];
        int[] lineTwo = new int[n];
        int[] transOne = new int[n-1];
        int[] transTwo = new int[n-1];
        int e1 = read.nextInt();
        int e2 = read.nextInt();
        int x1 = read.nextInt();
        int x2 = read.nextInt();
        for (int i = 0; i < n; i++){
            lineOne[i] = read.nextInt();
        }
        for (int i = 0; i < n; i++){
            lineTwo[i] = read.nextInt();
        }
        for (int i = 0; i < n-1; i++){
            transOne[i] = read.nextInt();
        }
        for (int i = 0; i < n-1; i++){
            transTwo[i] = read.nextInt();
        }

        BottomUpFactory(n, lineOne, lineTwo, transOne, transTwo, e1, e2, x1, x2);


    }

} 