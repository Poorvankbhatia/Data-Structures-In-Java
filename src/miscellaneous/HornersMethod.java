/*

Given a polynomial of the form cnxn + cn-1xn-1 + cn-2xn-2 + … + c1x + c0 and a value of x,
find the value of polynomial for a given value of x. Here cn, cn-1, .. are integers (may be negative) and n is a positive integer.

Input is in the form of an array say poly[] where poly[0] represents coefficient for xn and poly[1] represents coefficient for xn-1 and so on.

 */

package miscellaneous;

/**
 * Created by poorvank on 04/07/16.
 */
public class HornersMethod {

    private int x;

    public HornersMethod(int x) {
        this.x = x;
    }

    public int calculate(int[] pol) {

        int n = pol.length;
        int result = pol[0];

        for (int i=1;i<n;i++) {
            result = (result*x) + pol[i];
        }

        return result;
    }

    public static void main(String[] args) {

        // Let us evaluate value of 2x3 - 6x2 + 2x - 1 for x = 3
        int poly[] = {2, -6, 2, -1};
        int x = 3;
        HornersMethod hm = new HornersMethod(x);

        System.out.println(hm.calculate(poly));

    }

}

/*

Horner’s method can be used to evaluate polynomial in O(n) time.
To understand the method, let us consider the example of 2x3 – 6x2 + 2x – 1.
The polynomial can be evaluated as ((2x – 6)x + 2)x – 1. The idea is to initialize result as coefficient of xn which is 2 in this case,
repeatedly multiply result with x and add next coefficient to result. Finally return result.

 */
