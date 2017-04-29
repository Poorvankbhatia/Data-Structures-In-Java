/*

Temple Offerings
Consider a devotee wishing to give offerings to temples along a mountain range.
The temples are located in a row at different heights. Each temple should receive at least one offering.
If two adjacent temples are at different altitudes, then the temple that is higher up should receive more offerings
than the one that is lower down. If two adjacent temples are at the same height, then their offerings relative to each other does
not matter. Given the number of temples and the heights of the temples in order, find the minimum number of offerings to bring.

Examples:

Input  : 3
         1 2 2
Output : 4
All temples must receive at-least one offering.
Now, the second temple is at a higher altitude
compared to the first one. Thus it receives one
extra offering.
The second temple and third temple are at the
same height, so we do not need to modify the
offerings. Offerings given are therefore: 1, 2,
1 giving a total of 4.

Input  : 6x
         1 4 3 6 2 1
Output : 10
We can distribute the offerings in the following
way, 1, 2, 1, 3, 2, 1. The second temple has to
receive more offerings than the first due to its
height being higher. The fourth must receive more
than the fifth, which in turn must receive more
than the sixth. Thus the total becomes 10.

 */
package greedyProgramming;

import java.util.Arrays;

/**
 * Created by poorvank on 26/04/17.
 */
public class TempleOfferings {

    public int offerings(int n,int[] altitude) {

        int[] result = new int[n];

        Arrays.fill(result,1);

        for (int i=1;i<n;i++) {
            if(altitude[i]>altitude[i-1]) {
                result[i] = result[i-1]+1;
            }
        }

        for (int i=n-2;i>=0;i--) {
            if(altitude[i]>altitude[i+1]) {
                result[i] = Math.max(result[i],result[i+1]+1);
            }
        }

        int totalOffer = 0;

        for (int aResult : result) {
            totalOffer += aResult;
        }

        System.out.println(Arrays.toString(result));

        return totalOffer;

    }

    public static void main(String[] args) {
        int[] altitude = {1 ,2,2};
        System.out.println(new TempleOfferings().offerings(altitude.length,altitude));
    }

}

/*
Same as Candy Problem.
 */