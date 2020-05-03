import javax.swing.*;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int count = s.nextInt();
        int totalsize = 0;
        int[][] array = new int[count][];
        for (int i = 0; i < count; i++) {
            int size = s.nextInt();
            int[] arr = new int[size];
            for (int j = 0; j < size; j++) {
                arr[j] = s.nextInt();
            }
            mergeSort(arr, 0, arr.length);
            array[i] = arr;
            totalsize += arr.length;
        }
        int[] newArray = new int[totalsize];
        for (int i = 0; i < count; i++) {
            System.arraycopy(newArray,, array[i],
        }
    }
    public static void mergeSort(int[] array, int left, int right) {
        if (right <= left + 1) {
            return;
        }

        int middle = (left + right) / 2;

        mergeSort(array, left, middle);
        mergeSort(array, middle, right);

        merge(array, left, right, middle);
    }

    public static void merge(int[] array, int left, int right, int middle){
        int i = left;
        int j = middle;
        int k = 0;
        int[] temp = new int[middle];

        while (i < middle && j < right ) {
            if (array[i] < array[j]) {
                temp[k] = array[j];
                j++;
            } else {
                temp[k] = array[i];
                i++;
            }
            k++;
        }
        /* effective copying elements from temp to array */
        System.arraycopy(temp, 0, array, left, temp.length);
    }

}