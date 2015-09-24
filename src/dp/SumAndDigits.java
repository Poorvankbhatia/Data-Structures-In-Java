package dp;

/**
 * Created by poorvank on 9/9/15.
 */
public class SumAndDigits {

    public static void main(String[] args) {

        int n = 2, s = 4;
        System.out.println(noOfCombinations(n, s));

    }

    private static int noOfCombinations(int n, int s) {

        if (n == 0) {
            return 1;
        }

        int count = 0;

        for (int i = s; i >= 0; i--) {

            count += noOfCombinations(n - 1, s - i);

        }

        return count;

    }

}
