package arrays;

import java.util.Arrays;

/**
 * Created by poorvank on 6/12/15.
 */
public class BooleanArray {

    public static void main(String[] args) {

        int[] a = new int[]{0, 1};

        a[a[1]] = a[a[0]];

        System.out.println(Arrays.toString(a));

    }

}
