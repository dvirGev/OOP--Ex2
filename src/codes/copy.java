package codes;

public class copy {
    public static void copy(int[] arr, int[] arr2){
        for (int i = 0; i < arr.length; i++) {
            arr2[i] = arr[i];
        }
    }
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        int[] arr2 = new int[3];
        copy(arr,arr2);
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i]);
        }

    }
}
