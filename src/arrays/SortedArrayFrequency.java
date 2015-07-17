package arrays;

/**
 * Created by poorvank on 7/15/15.
 */
public class SortedArrayFrequency {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 1, 2, 2, 2, 2, 3, 3};

        int n = arr.length;

        int x = 2;

        int first = firstIndex(arr, x, 0, n - 1);
        int last = lastIndex(arr, x, 0, n - 1);

        if (first == -1 && last == -1) {
            System.out.println("No such element exists");
        } else if (first == last) {
            System.out.println("Only 1 occurrence found");
        } else {
            System.out.println(last - first + 1 + " occurrences found");
        }


    }

    private static int firstIndex(int[] arr, int x, int low, int high) {

        int mid = (high + low) / 2;

        if (high >= low) {

            if ((mid == 0 || x > arr[mid - 1]) && arr[mid] == x) {
                return mid;
            } else if (arr[mid] < x) {
                return firstIndex(arr, x, mid + 1, high);
            } else {
                return firstIndex(arr, x, low, mid - 1);
            }
        }
        return -1;

    }

    private static int lastIndex(int[] arr, int x, int low, int high) {

        int mid = (high + low) / 2;

        if (high >= low) {
            if ((mid == arr.length - 1 || x < arr[mid + 1]) && arr[mid] == x) {
                return mid;
            } else if (arr[mid] > x) {
                return lastIndex(arr, x, low, mid - 1);
            } else {
                return lastIndex(arr, x, mid + 1, high);
            }
        }

        return -1;

    }

}
