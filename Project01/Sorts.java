/*
* @Author: Chris Kim, Thinh Luu
* @Usernames: ckim65, tpluu
* @Date:   2018-01-10 09:16:14
* @Last Modified by:   tpluu
* @Last Modified time: 2018-01-19 15:03:20
*/
import java.util.Random;
import java.util.Arrays;

public class Sorts {
    public static void selectionSort(int[] arr, int N) {

        for (int i = 0; i < N-1; i++) {

            int temp = i;
            for (int j = i+1; j < N; j++) {

                if (arr[temp] > arr[j]) {

                    temp = j;

                }

            }
            swap(arr,i,temp);
        }
    }

    public static void mergeSort(int [] arr, int N) {
       
        mergeSort(arr, 0, (N-1));

    }

    private static void mergeSort(int[] list, int first, int last){

        if (first < last) {
          
            int middle = (first + last)/2;
            mergeSort(list, first, middle);
            mergeSort(list, (middle+1), last);
            mergeSortedHalves(list, first, middle, last);

        }
    }

    private static void mergeSortedHalves(int[] arr, int left, int middle, int right) {

       int[] temp = new int[(right-left+1)];
       int indexLeft = left;
       int indexRight = middle+1;
       int index = 0;

        while(indexLeft <= middle && indexRight <=right) {

            if (arr[indexLeft] < arr[indexRight]) {

                
                temp[index] = arr[indexLeft];
                index++;
                indexLeft++;

            }
            else if (arr[indexRight] < arr[indexLeft]) {

                temp[index] = arr[indexRight];
                index++;
                indexRight++;

            }
            else {

                temp[index] = arr[indexLeft];
                index++;
                indexLeft++;

            }
        }

        while (indexLeft <= middle) {

            temp[index] = arr[indexLeft];
            index++;
            indexLeft++;

        }
        while (indexRight <= right) {

            temp[index] = arr[indexRight];
            index++;
            indexRight++;

        }

        index = 0;
        for (int i = left; i <= right; i++) {
          
            arr[i] = temp[index];
            index++;

        }

    }

    public static void quickSort(int [] list, int N) {
       
        quickSort(list, 0, N-1);

    }

    public static void quickSort(int [] list, int first, int last) {
       
        if (first < last) {

            setPivotToEnd(list, first, last);
            int pivotIndex = splitList(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);

        }
    }

    private static void setPivotToEnd(int[] arr, int left, int right) {
       
        int center = (left + right)/2;

        if (arr[left] > arr[center]) {

            swap(arr,left,center);

        }
        if (arr[left] > arr[right]) {
            
            swap(arr,left,right);
        
        }
        if (arr[center] < arr[right]) {

            swap(arr,center,right);

        }
    }

    private static int splitList(int[] arr, int left, int right) {

        int indexLeft = left;
        int indexRight = right - 1;
        int pivot = arr[right];
        while (indexRight >= indexLeft) {

            while(arr[indexLeft] < pivot) {

                indexLeft++;

            }

            while (indexRight >= indexLeft) {

                if (arr[indexRight] > pivot) {

                    indexRight--;
                
                }
                else{
                    break;
                } 

            }

            if (indexRight >= indexLeft) {
                
                swap(arr, indexLeft, indexRight);
                indexLeft++;
                indexRight--;
            
            }

        }

        swap(arr, indexLeft, right);
        return indexLeft;
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

        System.out.println();
        System.out.println();
        System.out.println();

        for (int i = 0; i < 10;  i++) {

          arr[i] = rand.nextInt(50) + 1;

        }

        System.out.println("ORIGINAL ARRAY: " + Arrays.toString(arr));

        mergeSort(arr, arr.length);

        System.out.println("NEW ARRAY: " + Arrays.toString(arr));

        System.out.println();
        System.out.println();
        System.out.println();

        for (int i = 0; i < 10;  i++) {

          arr[i] = rand.nextInt(20) + 1;

        }
        //arr = new int[] {20, 10, 3, 14, 7, 12, 15, 2, 8, 12};
        int[] arr2 = new int[10]; //{20, 10, 3, 14, 7, 12, 15, 2, 8, 12};
        System.arraycopy(arr, 0 , arr2, 0, arr.length);

        System.out.println("ORIGINAL ARRAY: " + Arrays.toString(arr));

        mergeSort(arr2, arr.length);
        quickSort(arr, arr.length);

        System.out.println("NEW ARRAY AR2: " + Arrays.toString(arr2));
        System.out.println("NEW ARRAY ARR: " + Arrays.toString(arr));

    }

}