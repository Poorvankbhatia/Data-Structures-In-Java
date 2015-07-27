package arrays;

import java.util.Arrays;

/**
 * Created by poorvank on 6/6/15.
 */
public class TripletsArray {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 4, 15, 6, 10, 8};
        int sum = 22;

        Arrays.sort(arr);

        for (int i = 0; i < arr.length; i++) {

            int l = i + 1;
            int r = arr.length - 1;

            while (l < r) {

                if (arr[i] + arr[l] + arr[r] == sum) {
                    System.out.println(arr[i] + " " + arr[l] + " " + arr[r]);
                    l++;
                    r--;
                } else if (arr[i] + arr[l] + arr[r] < sum) {
                    l++;
                } else {
                    r--;
                }
            }

        }

    }

}
