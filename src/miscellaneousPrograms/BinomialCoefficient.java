/*

Write a function that takes two parameters n and k and returns the value of Binomial Coefficient
C(n, k). For example, your function should return 6 for n = 4 and k = 2, and it should return 10 for n = 5 and k = 2.

 */

package miscellaneousPrograms;

/**
 * Created by poorvank on 7/5/15.
 */
public class BinomialCoefficient {

    public static void main(String[] args) {

        int n = 8, k = 2;

        System.out.println(calculateBinomialCoefficient(8, 2));

    }

    private static int calculateBinomialCoefficient(int n, int k) {

        if (k < n - k) {
            k = n - k;
        }

        int res = 1;

        for (int i = 0; i < k; i++) {

            res *= (n - i);
            res /= (i + 1);

        }

        return res;

    }

}

/*

he value of C(n, k) can be calculated in O(k) time and O(1) extra space.

C(n, k) = n! / (n-k)! * k!
        = [n * (n-1) *....* 1]  / [ ( (n-k) * (n-k-1) * .... * 1) *
                                    ( k * (k-1) * .... * 1 ) ]
After simplifying, we get
C(n, k) = [n * (n-1) * .... * (n-k+1)] / [k * (k-1) * .... * 1]

Also, C(n, k) = C(n, n-k)  // we can change r to n-r if r > n-r 

 */