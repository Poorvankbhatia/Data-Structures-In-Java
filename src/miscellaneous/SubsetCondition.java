package miscellaneous;

import java.util.TreeSet;

/**
 * Created by poorvank.b on 02/12/17.
 */
public class SubsetCondition {

    public static int count(int[] arr) {

        TreeSet<Integer> set = new TreeSet<>();
        for (int element : arr) {
            set.add(element);
        }

        int n = arr.length;
        int[] greaterOnRight = new int[n];
        int[] greaterOnLeft  = new int[n];
        int[] smallerOnRight = new int[n];
        int[] smallerOnLeft  = new int[n];

        smallerOnLeft[0]=-1;
        greaterOnLeft[0]=-1;
        smallerOnRight[n-1]=-1;
        greaterOnRight[n-1]=-1;

       return 1;
    }

}
