import java.lang.reflect.Array;
import java.util.ArrayList;

public class permute {
    private static void permute(ArrayList<int[]> arrayList, int[] arr, int k) {
        for (int i = k; i < arr.length; i++) {
            swap(arr, i, k);
            permute(arrayList, arr, k + 1);
            swap(arr, k, i);
        }
        if (k == arr.length - 1) {
            arrayList.add(deepCopy(arr));
        }
    }

    private static int[] deepCopy(int[] arr) {
        int[] newArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    private static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print("" + arr[i] + ",");
        }
        System.out.println();
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        ArrayList<int[]> arrayList = new ArrayList<int[]>();
        int[] arr = {1, 2, 3, 4};
        int[] arr2 = {1, 5, 3, 4};
        int[] arr3 = {1, 1, 3, 4};

        permute(arrayList, arr, 0);
        for (int i = 0; i < arrayList.size(); i++) {
            printArr(arrayList.get(i));
        }
    }
}